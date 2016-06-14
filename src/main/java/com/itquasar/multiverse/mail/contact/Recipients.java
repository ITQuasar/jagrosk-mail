/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.contact;

import com.itquasar.multiverse.mail.message.envelope.Envelope;
import com.itquasar.multiverse.mail.util.Constants;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface Recipients extends Constants {

    static Recipients copyTo(Envelope envelope) {
        return new ImmutableRecipients(envelope.getTo());
    }

    static Recipients copyToAndCc(Envelope envelope) {
        return new ImmutableRecipients(envelope.getTo(), envelope.getCc());
    }

    static Recipients copyToAndBcc(Envelope envelope) {
        return new ImmutableRecipients(envelope.getTo(), NO_ONES_LIST, envelope.getBcc());
    }

    static Recipients copyToAndCcAndBcc(Envelope envelope) {
        return new ImmutableRecipients(envelope.getTo(), envelope.getCc(), envelope.getBcc());
    }

    static Recipients copyCc(Envelope envelope) {
        return new ImmutableRecipients(NO_ONES_LIST, envelope.getCc());
    }

    static Recipients copyCcAndBcc(Envelope envelope) {
        return new ImmutableRecipients(NO_ONES_LIST, envelope.getCc(), envelope.getBcc());
    }

    static Recipients copyBcc(Envelope envelope) {
        return new ImmutableRecipients(NO_ONES_LIST, NO_ONES_LIST, envelope.getBcc());
    }

    Iterable<Contact> getTo();

    Iterable<Contact> getCc();

    Iterable<Contact> getBcc();

}
