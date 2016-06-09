package com.itquasar.multiverse.mail.message;

import com.itquasar.multiverse.mail.api.Content;
import com.itquasar.multiverse.mail.api.Email;
import com.itquasar.multiverse.mail.api.Envelope;
import com.itquasar.multiverse.mail.exception.EmailException;
import com.itquasar.multiverse.mail.message.content.LazyContent;
import com.itquasar.multiverse.mail.message.envelope.LazyEnvelope;
import com.itquasar.multiverse.mail.util.EmailUtils;
import com.itquasar.multiverse.mail.util.FunctionUtils;
import java.util.UUID;
import javax.mail.Message;
import javax.mail.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class ImmutableEmail implements Email {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImmutableEmail.class);

    private UUID uuid = UUID.randomUUID();

    private Message message;
    private final Envelope envelope;
    private final Content content;

    //   private final List<Part> parts = new LinkedList<>();
    public ImmutableEmail(Envelope envelope, Content content) {
        this.message = null;
        this.envelope = envelope;
        this.content = content;
    }

    /**
     * Build an {@link Email} based on the given {@link Message}.
     *
     * @param message The message to use as source.
     */
    public ImmutableEmail(Message message) {
        try {
            this.message = message;
            this.envelope = new LazyEnvelope(message);
            this.content = new LazyContent(message);
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

    /**
     *
     * @return The size of wrapped message or -1 when no message wrapped.
     */
    @Override
    public int getSize() {
        return FunctionUtils.defaultOnNullOrException(() -> message.getSize(), -1, LOGGER);
    }

    @Override
    public Message toMessage(Session session) {
        return EmailUtils.toMessage(this, session);
    }

    @Override
    public String toString() {
        return "Email{" + "uuid=" + uuid + ", envelope=" + envelope + ", content=" + content + '}';
    }

}
