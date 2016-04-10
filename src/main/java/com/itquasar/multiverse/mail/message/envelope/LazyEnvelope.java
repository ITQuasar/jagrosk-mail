package com.itquasar.multiverse.mail.message.envelope;

import com.itquasar.multiverse.mail.api.Envelope;
import com.itquasar.multiverse.mail.api.Contact;
import com.itquasar.multiverse.mail.util.Parser;
import java.util.Date;
import java.util.List;
import javax.mail.Message;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class LazyEnvelope implements Envelope {

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
    public Contact getSender() {
        this.init();
        return envelope.getSender();
    }

    @Override
    public List<Contact> getFrom() {
        this.init();
        return envelope.getFrom();
    }

    @Override
    public List<Contact> getReplyTo() {
        this.init();
        return envelope.getReplyTo();
    }

    @Override
    public List<Contact> getTo() {
        this.init();
        return envelope.getTo();
    }

    @Override
    public List<Contact> getCc() {
        this.init();
        return envelope.getCc();
    }

    @Override
    public List<Contact> getBcc() {
        this.init();
        return envelope.getBcc();
    }

    @Override
    public String getSubject() {
        this.init();
        return envelope.getSubject();
    }

    @Override
    public Date getReceivedOn() {
        this.init();
        return envelope.getReceivedOn();
    }

}
