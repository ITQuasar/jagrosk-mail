package com.itquasar.multiverse.lib.mail;

import com.itquasar.multiverse.lib.mail.content.LazyContent;
import com.itquasar.multiverse.lib.mail.envelope.ImmutableEnvelope;
import com.itquasar.multiverse.lib.mail.envelope.LazyEnvelope;
import com.itquasar.multiverse.lib.mail.exception.EmailException;
import static com.itquasar.multiverse.lib.mail.part.MimeTypes.*;
import com.itquasar.multiverse.lib.mail.part.Part;
import com.itquasar.multiverse.lib.mail.util.ClientUtils;
import com.itquasar.multiverse.lib.mail.util.Constants;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class Email {

    private static final Logger LOGGER = LoggerFactory.getLogger(Email.class);

    private UUID uuid = UUID.randomUUID();

    private final Envelope envelope;
    private final Content content;

    //   private final List<Part> parts = new LinkedList<>();
    public Email(Envelope envelope, Content content) {
        this.envelope = envelope;
        this.content = content;
    }

    /**
     * Build an {@link Email} based on the given {@link Message}.
     *
     * @param message The message to use as source.
     */
    public Email(Message message) {
        try {
            this.envelope = new LazyEnvelope(message);
            this.content = new LazyContent(message);
        } catch (Exception ex) {
            throw new EmailException("Could not initialize Email from Message.", ex);
        }
    }

    public Envelope getEnvelope() {
        return envelope;
    }

    public Content getContent() {
        return content;
    }

    /**
     * Create an email using this message, the content supplied and {@code from}
     * supplied as from ad {@code replyTo}
     *
     * @param from
     * @param content
     * @return A new Email instance.
     */
    // FIXME: too much side effects
    public Email reply(EmailContact from, Content content) {
        String subject = getEnvelope().getSubject();
        if (!subject.startsWith("Re:")) {
            subject = "Re: " + subject;
        }
        return new Email(
                new ImmutableEnvelope(
                        from,
                        getEnvelope().getReplyTo(),
                        getEnvelope().getCc(),
                        getEnvelope().getBcc(),
                        subject,
                        null
                ),
                content
        );
    }

    /**
     * Create an email using this message, the content supplied adding all
     * attachments to it, {@code from} supplied as from ad {@code replyTo} and
     * {@code to} as recipients
     *
     * @param from
     * @param content
     * @param to
     * @return A new Email instance.
     */
    // FIXME: too much side effects
    public Email forward(EmailContact from, Content content, EmailContact... to) {
        // add original attachments
        content.getAttachments().addAll(this.getContent().getAttachments());

        String subject = getEnvelope().getSubject();
        if (!subject.startsWith("Fwd:")) {
            subject = "Fwd: " + subject;
        }
        return new Email(
                new ImmutableEnvelope(
                        from,
                        ClientUtils.emailContactToList(to),
                        Constants.NO_ONES,
                        Constants.NO_ONES,
                        subject,
                        null
                ),
                content
        );
    }

    public Message toMessage(Session session) {
        MimeMessage message = new MimeMessage(session);
        try {
            // HEADERS
            message.setSender(envelope.getSender().toInternetAddress());
            message.addFrom(EmailContact.toInternetAddresses(envelope.getFrom()));
            message.setReplyTo(EmailContact.toInternetAddresses(envelope.getReplyTo()));
            message.setRecipients(Message.RecipientType.TO, EmailContact.toInternetAddresses(envelope.getTo()));
            message.setRecipients(Message.RecipientType.CC, EmailContact.toInternetAddresses(envelope.getCc()));
            message.setRecipients(Message.RecipientType.BCC, EmailContact.toInternetAddresses(envelope.getBcc()));
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

    @Override
    public String toString() {
        return "Email{" + "uuid=" + uuid + ", envelope=" + envelope + ", content=" + content + '}';
    }

}
