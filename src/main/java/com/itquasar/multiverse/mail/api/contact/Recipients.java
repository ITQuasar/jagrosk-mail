/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.api.contact;

import com.itquasar.multiverse.mail.api.Envelope;
import com.itquasar.multiverse.mail.util.Constants;
import java.util.List;

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
        return new ImmutableRecipients(envelope.getTo(), NO_ONES, envelope.getBcc());
    }

    static Recipients copyToAndCcAndBcc(Envelope envelope) {
        return new ImmutableRecipients(envelope.getTo(), envelope.getCc(), envelope.getBcc());
    }

    static Recipients copyCc(Envelope envelope) {
        return new ImmutableRecipients(NO_ONES, envelope.getCc());
    }

    static Recipients copyCcAndBcc(Envelope envelope) {
        return new ImmutableRecipients(NO_ONES, envelope.getCc(), envelope.getBcc());
    }

    static Recipients copyBcc(Envelope envelope) {
        return new ImmutableRecipients(NO_ONES, NO_ONES, envelope.getBcc());
    }

    List<Contact> getTo();

    List<Contact> getCc();

    List<Contact> getBcc();

}
