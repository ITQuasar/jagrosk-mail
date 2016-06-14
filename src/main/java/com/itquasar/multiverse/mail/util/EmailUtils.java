package com.itquasar.multiverse.mail.util;

import com.itquasar.multiverse.mail.message.content.Content;
import com.itquasar.multiverse.mail.message.Email;
import com.itquasar.multiverse.mail.message.envelope.Envelope;
import com.itquasar.multiverse.mail.contact.Contact;
import com.itquasar.multiverse.mail.contact.ImmutableRecipients;
import com.itquasar.multiverse.mail.contact.Recipients;
import com.itquasar.multiverse.mail.contact.Senders;
import com.itquasar.multiverse.mail.exception.EmailException;
import com.itquasar.multiverse.mail.message.ImmutableEmail;
import com.itquasar.multiverse.mail.message.content.ImmutableContent;
import com.itquasar.multiverse.mail.message.envelope.ImmutableEnvelope;
import com.itquasar.multiverse.mail.part.MimeTypes;
import static com.itquasar.multiverse.mail.part.MimeTypes.MULTIPART_ALTERNATIVE;
import static com.itquasar.multiverse.mail.part.MimeTypes.MULTIPART_MIXED;
import static com.itquasar.multiverse.mail.part.MimeTypes.MULTIPART_RELATED;
import com.itquasar.multiverse.mail.part.Part;
import com.itquasar.multiverse.mail.part.SinglePart;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.itquasar.multiverse.mail.message.TemplatedSubjectAndContent;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class EmailUtils {

    private EmailUtils() {
    }

    public static Message toMessage(Email email, Session session) {
        MimeMessage message = new MimeMessage(session);
        Envelope envelope = email.getEnvelope();
        Content content = email.getContent();
        try {
            // HEADERS
            message.setSender(envelope.getSender().toInternetAddress());
            message.addFrom(Contact.toInternetAddresses(envelope.getFrom()));
            message.setReplyTo(Contact.toInternetAddresses(envelope.getReplyTo()));
            message.setRecipients(Message.RecipientType.TO, Contact.toInternetAddresses(envelope.getTo()));
            message.setRecipients(Message.RecipientType.CC, Contact.toInternetAddresses(envelope.getCc()));
            message.setRecipients(Message.RecipientType.BCC, Contact.toInternetAddresses(envelope.getBcc()));
            message.setSubject(envelope.getSubject());
            // BODY
            MimeMultipart multipartRelated = null;
            MimeMultipart multipartAlternative = null;
            MimeMultipart multipartMixed = null;
            if (content.hasTextHtml()) {
                // multipart/related
                if (content.hasImages()) {
                    List<Part> parts = new LinkedList<>();
                    parts.add(content.getHtmlPart());
                    parts.addAll(content.getHtmlImages());
                    multipartRelated = ClientUtils.buildeMultipart(MULTIPART_RELATED.getSubType(), parts);
                }
                // multipart alternative
                if (content.hasTextHtml() && content.hasTextPlain()) {
                    // has no images (multipart/related)
                    if (multipartRelated == null) {
                        multipartAlternative = ClientUtils.buildeMultipart(
                                MULTIPART_RELATED.getSubType(),
                                content.getTextPart(), content.getHtmlPart()
                        );
                    } else {
                        // has images (mutipart/related)
                        multipartAlternative = ClientUtils.buildeMultipart(
                                MULTIPART_RELATED.getSubType(),
                                content.getTextPart()
                        );
                        multipartAlternative.addBodyPart(
                                ClientUtils.buildMultipartBody(MULTIPART_RELATED.getMimeType(), multipartRelated)
                        );
                    }
                }
            }
            // is mixed
            if (!content.getAttachments().isEmpty()) {
                MimeBodyPart mainContentPart = null;
                // if main content is not multipart is only html or plain text
                if (multipartAlternative == null && multipartRelated == null) {
                    if (content.hasTextHtml()) {
                        mainContentPart = ClientUtils.partToMimeBodyPart(content.getHtmlPart());
                    } else if (content.hasTextPlain()) {
                        mainContentPart = ClientUtils.partToMimeBodyPart(content.getTextPart());
                    }
                }
                // add attachments
                multipartMixed = ClientUtils.buildeMultipart(MULTIPART_MIXED.getSubType(), content.getAttachments());
                // add main content if exists
                if (mainContentPart != null) {
                    multipartMixed.addBodyPart(mainContentPart, 0);
                } else if (multipartAlternative != null) {
                    multipartMixed.addBodyPart(ClientUtils.buildMultipartBody(MULTIPART_ALTERNATIVE, multipartAlternative), 0);
                } else if (multipartRelated != null) {
                    multipartMixed.addBodyPart(ClientUtils.buildMultipartBody(MULTIPART_RELATED, multipartRelated), 0);
                }
            }
            // final touch: add correct content to message body
            MimeMultipart multipart
                    = multipartMixed != null
                            ? multipartMixed
                            : (multipartAlternative != null ? multipartAlternative : multipartRelated);
            if (multipart != null) {
                message.setContent(multipart);
            } else if (content.hasTextHtml()) {
                message.setText(content.getHtmlContent(), null, "html");
            } else if (content.hasTextPlain()) {
                message.setText(content.getTextContent(), null, "plain");
            }
            message.saveChanges();
        } catch (MessagingException ex) {
            throw new EmailException("Error generating Message from Email.", ex);
        }
        return message;

    }

    public static Email reply(Email email, Senders senders, TemplatedSubjectAndContent content) {
        return reply(email, senders, content, false);
    }

    public static Email replyAll(Email email, Senders senders, TemplatedSubjectAndContent content, boolean replayAll) {
        return reply(email, senders, content, true);
    }

    public static Email reply(Email email, Senders senders, TemplatedSubjectAndContent content, boolean replayAll) {
        String subject = email.getEnvelope().getSubject();
        if (!subject.startsWith("Re:")) {
            subject = "Re: " + subject;
        }
        return new ImmutableEmail(
                new ImmutableEnvelope(
                        senders,
                        new ImmutableRecipients(
                                replayAll
                                        ? join(email.getEnvelope().getReplyTo(), email.getEnvelope().getFrom(), true)
                                        : email.getEnvelope().getReplyTo(),
                                replayAll ? email.getEnvelope().getCc() : Constants.NO_ONES_LIST,
                                replayAll ? email.getEnvelope().getBcc() : Constants.NO_ONES_LIST
                        ),
                        subject
                ),
                new ImmutableContent(
                        new SinglePart<>(
                                MimeTypes.TEXT_PLAIN,
                                content.getTextContent() + quoteText(email.getContent().getTextContent())
                        ),
                        new SinglePart<>(
                                MimeTypes.TEXT_HTML,
                                content.getHtmlContent() + quoteHtml(email.getContent().getHtmlContent())
                        ),
                        join(email.getContent().getHtmlImages(), content.getHtmlImages()),
                        email.getContent().getAttachments()
                )
        );
    }

    public static Email forward(Email email, Senders senders, TemplatedSubjectAndContent content, Recipients recipients) {
        String subject = email.getEnvelope().getSubject();
        if (!subject.startsWith("Fwd:")) {
            subject = "Fwd: " + subject;
        }
        return new ImmutableEmail(
                new ImmutableEnvelope(
                        senders,
                        recipients,
                        subject
                ),
                new ImmutableContent(
                        new SinglePart<>(
                                MimeTypes.TEXT_PLAIN,
                                content.getTextContent() + quoteText(email.getContent().getTextContent())
                        ),
                        new SinglePart<>(
                                MimeTypes.TEXT_HTML,
                                content.getHtmlContent() + quoteHtml(email.getContent().getHtmlContent())
                        ),
                        join(email.getContent().getHtmlImages(), content.getHtmlImages()),
                        email.getContent().getAttachments()
                )
        );
    }

    private static <T> List<T> join(List<T> list1, List<T> list2) {
        return join(list1, list2, false);
    }

    private static <T> List<T> join(List<T> list1, List<T> list2, boolean removeDoubles) {
        List<T> newList = new LinkedList<>();
        newList.addAll(list1);
        newList.addAll(list2);
        if (removeDoubles) {
            newList = new LinkedList<>(new HashSet<>(newList));
        }
        return newList;
    }

    private static String quoteText(String text) {
        String prefix = "\n> ";
        return prefix + text.replaceAll("(\r\n|\n|\r)", prefix);
    }

    private static String quoteHtml(String html) {
        return "<blockquote>\n" + html + "</blockquote>\n";
    }

}
