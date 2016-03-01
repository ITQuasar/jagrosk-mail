package com.itquasar.multiverse.lib.mail;

import static com.itquasar.multiverse.lib.mail.GenericPart.EMPTY_PART;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimePart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class Email {

    private static final Logger LOGGER = LoggerFactory.getLogger(Email.class);

    // FIXME
    private UUID uuid = UUID.randomUUID();

    private String subject = null;

    private final List<Address> from = new LinkedList<>();
    private final List<Address> replyTo = new LinkedList<>();
    private final List<Address> to = new LinkedList<>();
    private final List<Address> cc = new LinkedList<>();
    private final List<Address> bcc = new LinkedList<>();

    private final List<Part> parts = new LinkedList<>();

    public Email() {
    }

    /**
     * Build an {@link Email} based on the given {@link Message}.
     *
     * @param message The messge to use as source.
     */
    public Email(Message message) {
        try {
            this.subject = message.getSubject();

            Address[] addresses;

            addresses = message.getFrom();
            if (addresses != null) {
                this.from.addAll(Arrays.asList());
            }

            addresses = message.getReplyTo();
            if (addresses != null) {
                this.replyTo.addAll(Arrays.asList());
            }

            addresses = message.getRecipients(Message.RecipientType.TO);
            if (addresses != null) {
                this.to.addAll(Arrays.asList());
            }

            addresses = message.getRecipients(Message.RecipientType.CC);
            if (addresses != null) {
                this.cc.addAll(Arrays.asList());
            }

            addresses = message.getRecipients(Message.RecipientType.BCC);
            if (addresses != null) {
                this.bcc.addAll(Arrays.asList());
            }

            parseParts(message, parts);
        } catch (MessagingException | IOException ex) {
            LOGGER.error("Error parsing javax.mail.Message [{}]", message, ex);
        }
    }

    public String getSubject() {
        return subject;
    }

    public List<Address> getReplyTo() {
        return replyTo;
    }

    public List<Address> getFrom() {
        return from;
    }

    public List<Address> getTo() {
        return to;
    }

    public List<Address> getCc() {
        return cc;
    }

    public List<Address> getBcc() {
        return bcc;
    }

    public List<Part> getParts() {
        return parts;
    }

    /**
     * Try HTML content, otherwise TEXT content.
     *
     * @return
     */
    public Part getContent() {
        Part htmlPart = getContent(parts, true);
        return htmlPart.getDisposition().equals(Part.Disposition.NONE) ? getContent(parts, false) : htmlPart;
    }

    public Part getContent(List<Part> partToSearch, boolean html) {
        for (Part part : partToSearch) {
            if (part.getDisposition().equals(Part.Disposition.FLOWED)) {
                if ((!html && part.getMimeType().startsWith("text/plain"))
                        || (html && part.getMimeType().startsWith("text/html"))) {
                    return part;
                }
            }
        }
        return EMPTY_PART;
    }

    public String getContentAsString() {
        return (String) getContent().getContent();
    }

    public String getTextContent() {
        return (String) getContent(parts, false).getContent();
    }

    public String getHtmlContent() {
        return (String) getContent(parts, true).getContent();
    }

    @Override
    public String toString() {
        return "Email{" + "uuid=" + uuid + ", subject=" + subject + ", from=" + from + ", replyTo=" + replyTo + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc + ", attachments=" + parts + '}';
    }

    private static void parseParts(javax.mail.Part p, List<Part> parts) throws MessagingException, IOException {
        // chek if the content is multipert
        if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) p.getContent();
            int count = mp.getCount();
            // FIXME -> multipart should be a list of parts too?
            for (int i = 0; i < count; i++) {
                parseParts(mp.getBodyPart(i), parts);
            }
        } //check if the content is a nested message
        else if (p.isMimeType("message/rfc822")) {
            parseParts((javax.mail.Part) p.getContent(), parts);
        } else {
            MimePart mp = MimePart.class.cast(p);
            parts.add(new GenericPart(mp.getContentID(), mp.getDisposition(), p.getFileName(), p.getContentType(), p.getContent()));
        }
    }
}
