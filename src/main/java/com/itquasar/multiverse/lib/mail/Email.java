package com.itquasar.multiverse.lib.mail;

import com.itquasar.multiverse.lib.mail.exception.EmailException;
import java.util.UUID;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
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
            InternetAddress[] from = (InternetAddress[]) message.getFrom();
            InternetAddress[] replyTo = (InternetAddress[]) message.getReplyTo();
            InternetAddress[] to = (InternetAddress[]) message.getRecipients(Message.RecipientType.TO);
            InternetAddress[] cc = (InternetAddress[]) message.getRecipients(Message.RecipientType.CC);
            InternetAddress[] bcc = (InternetAddress[]) message.getRecipients(Message.RecipientType.BCC);
            this.envelope = new Envelope(from, replyTo, to, cc, bcc);
            this.content = new Content(message);
        } catch (MessagingException ex) {
            LOGGER.error("Error parsing javax.mail.Message [{}]", message, ex);
            throw new EmailException("Could not initialize Email from Message.", ex);
        }
    }

    public Envelope getEnvelope() {
        return envelope;
    }

    public Content getContent() {
        return content;
    }

//
//
//    /**
//     * Try HTML content, otherwise TEXT content.
//     *
//     * @return
//     */
//    public Part getContent() {
//        Part htmlPart = getContent(parts, true);
//        return htmlPart.getDisposition().equals(Part.Disposition.NONE) ? getContent(parts, false) : htmlPart;
//    }
//
//    public Part getContent(List<Part> partToSearch, boolean html) {
//        for (Part part : partToSearch) {
//            if (part.getDisposition().equals(Part.Disposition.FLOWED)) {
//                if ((!html && part.getMimeType().startsWith("text/plain"))
//                        || (html && part.getMimeType().startsWith("text/html"))) {
//                    return part;
//                }
//            }
//        }
//        return EMPTY_PART;
//    }
//
//    public String getContentAsString() {
//        return (String) getContent().getContent();
//    }
//
//    public String getTextContent() {
//        return (String) getContent(parts, false).getContent();
//    }
//
//    public String getHtmlContent() {
//        return (String) getContent(parts, true).getContent();
//    }
//
//    // FIXME: think and create send API
//    public boolean send(Session session) {
//        throw new UnsupportedOperationException("Not implemented yet!");
//    }
//
//    // FIXME: think and create send API
//    public boolean send() {
//        throw new UnsupportedOperationException("Not implemented yet!");
//    }
//    @Override
//    public String toString() {
//        return "Email{" + "uuid=" + uuid + ", subject=" + subject + ", from=" + from + ", replyTo=" + replyTo + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc + ", attachments=" + parts + '}';
//    }
    @Override
    public String toString() {
        return "Email{" + "uuid=" + uuid + ", envelope=" + envelope + ", content=" + content + '}';
    }

}
