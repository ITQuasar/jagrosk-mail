package com.itquasar.multiverse.lib.mail;

import com.itquasar.multiverse.lib.mail.exception.EmailException;
import com.itquasar.multiverse.lib.mail.util.Parser;
import java.util.UUID;
import javax.mail.Message;
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
            this.envelope = Parser.parseMessageEnvelope(message);
            this.content = Parser.parseMessageContent(message);
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
