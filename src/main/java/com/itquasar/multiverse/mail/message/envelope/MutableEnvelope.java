/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message.envelope;

import com.itquasar.multiverse.mail.contact.MutableRecipients;
import com.itquasar.multiverse.mail.contact.MutableSenders;
import com.itquasar.multiverse.mail.contact.Recipients;
import com.itquasar.multiverse.mail.contact.Senders;
import java.time.Instant;
import java.util.Optional;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class MutableEnvelope extends AbstractEnvelope {

    private Senders senders;
    private Recipients recipients;
    private String subject;
    private Instant receivedOn;

    public MutableEnvelope() {
        this(null, null, null);
    }

    public MutableEnvelope(Senders senders, Recipients recipients, String subject) {
        this(senders, recipients, subject, null);
    }

    public MutableEnvelope(Senders senders, Recipients recipients, String subject, Instant receivedOn) {
        this.senders = senders == null ? new MutableSenders() : senders;
        this.recipients = recipients == null ? new MutableRecipients() : recipients;
        this.subject = subject == null ? EMPTY_STRING : subject;
        this.receivedOn = receivedOn;
    }

    @Override
    public Senders getSenders() {
        return senders;
    }

    @Override
    public Recipients getRecipients() {
        return recipients;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public Optional<Instant> getReceivedOn() {
        return Optional.ofNullable(receivedOn);
    }

    public void setSenders(Senders senders) {
        this.senders = senders;
    }

    public void setRecipients(Recipients recipients) {
        this.recipients = recipients;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setReceivedOn(Instant receivedOn) {
        this.receivedOn = receivedOn;
    }

}
