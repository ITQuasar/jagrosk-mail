package com.itquasar.multiverse.mail.message;

import com.itquasar.multiverse.mail.exception.EmailException;
import com.itquasar.multiverse.mail.message.content.Content;
import com.itquasar.multiverse.mail.message.content.LazyContent;
import com.itquasar.multiverse.mail.message.envelope.Envelope;
import com.itquasar.multiverse.mail.message.envelope.LazyEnvelope;
import java.util.UUID;
import javax.mail.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class ImmutableParsedEmail implements ParsedEmail {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImmutableParsedEmail.class);

    private UUID uuid = UUID.randomUUID();

    private Message message;
    private final ImmutableEmail email;

    /**
     * Build an {@link Email} based on the given {@link Message}.
     *
     * @param message The message to use as source.
     */
    public ImmutableParsedEmail(Message message) {
        try {
            this.message = message;
            this.email = new ImmutableEmail(
                    new LazyEnvelope(message),
                    new LazyContent(message)
            );
        } catch (Exception ex) {
            throw new EmailException("Could not initialize Email from Message.", ex);
        }
    }

    /**
     *
     * @return The message envelope.
     */
    @Override
    public Envelope getEnvelope() {
        return email.getEnvelope();
    }

    /**
     *
     * @return The message content.
     */
    @Override
    public Content getContent() {
        return email.getContent();
    }

    @Override
    public Message unwrap() {
        return message;
    }

    @Override
    public String toString() {
        return "Email{" + "uuid=" + uuid + ", envelope=" + getEnvelope() + ", content=" + getContent() + '}';
    }

}
