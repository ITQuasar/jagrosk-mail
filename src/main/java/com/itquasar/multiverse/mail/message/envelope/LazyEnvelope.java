package com.itquasar.multiverse.mail.message.envelope;

import com.itquasar.multiverse.mail.contact.Recipients;
import com.itquasar.multiverse.mail.contact.Senders;
import com.itquasar.multiverse.mail.util.Parser;
import java.time.Instant;
import java.util.Optional;
import javax.mail.Message;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class LazyEnvelope extends AbstractEnvelope {

    private ImmutableEnvelope envelope = null;
    private final Message message;

    public LazyEnvelope(Message message) {
        this.message = message;
    }

    private void init() {
        if (envelope == null) {
            envelope = Parser.parseMessageEnvelope(message);
        }
    }

    @Override
    public Senders getSenders() {
        this.init();
        return envelope.getSenders();
    }

    @Override
    public Recipients getRecipients() {
        this.init();
        return envelope.getRecipients();
    }

    @Override
    public String getSubject() {
        this.init();
        return envelope.getSubject();
    }

    @Override
    public Optional<Instant> getReceivedOn() {
        this.init();
        return envelope.getReceivedOn();
    }

}
