package com.itquasar.multiverse.mail.message;

import com.itquasar.multiverse.mail.message.content.Content;
import com.itquasar.multiverse.mail.message.envelope.Envelope;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class ImmutableEmail implements Email {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImmutableEmail.class);

    private UUID uuid = UUID.randomUUID();

    private final Envelope envelope;
    private final Content content;

    public ImmutableEmail(Envelope envelope, Content content) {
        this.envelope = envelope;
        this.content = content;
    }

    /**
     *
     * @return The message envelope.
     */
    @Override
    public Envelope getEnvelope() {
        return envelope;
    }

    /**
     *
     * @return The message content.
     */
    @Override
    public Content getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Email{" + "uuid=" + uuid + ", envelope=" + envelope + ", content=" + content + '}';
    }

}
