package com.itquasar.multiverse.lib.mail;

import com.itquasar.multiverse.lib.mail.envelope.ImmutableEnvelope;
import com.itquasar.multiverse.lib.mail.envelope.LazyEnvelope;
import com.itquasar.multiverse.lib.mail.exception.EmailException;
import com.itquasar.multiverse.lib.mail.util.Constants;
import com.itquasar.multiverse.lib.mail.util.Utils;
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
     * @return
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
     * @return
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
                        Utils.emailContactToList(to),
                        Constants.NO_ONES,
                        Constants.NO_ONES,
                        subject,
                        null
                ),
                content
        );
    }

    public Message toMessage() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public String toString() {
        return "Email{" + "uuid=" + uuid + ", envelope=" + envelope + ", content=" + content + '}';
    }

}
