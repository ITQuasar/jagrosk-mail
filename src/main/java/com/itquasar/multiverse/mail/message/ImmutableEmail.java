package com.itquasar.multiverse.mail.message;

import com.itquasar.multiverse.mail.message.content.Content;
import com.itquasar.multiverse.mail.message.envelope.Envelope;
import com.itquasar.multiverse.mail.message.flag.EmailFlag;
import java.util.Collections;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class ImmutableEmail implements BuildedEmail {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImmutableEmail.class);

    private UUID uuid = UUID.randomUUID();

    private final Envelope envelope;
    private final Content content;
    private final Iterable<EmailFlag> flags;

    public ImmutableEmail(Envelope envelope, Content content) {
        this(envelope, content, Collections.EMPTY_SET);
    }

    public ImmutableEmail(Envelope envelope, Content content, Iterable<EmailFlag> flags) {
        this.envelope = envelope;
        this.content = content;
        this.flags = flags;
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
    public Iterable<EmailFlag> getFlags() {
        return flags;
    }

    @Override
    public String toString() {
        return "Email{" + "uuid=" + uuid + ", envelope=" + envelope + ", content=" + content + '}';
    }

}
