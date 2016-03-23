/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.lib.mail.envelope;

import com.itquasar.multiverse.lib.mail.EmailContact;
import com.itquasar.multiverse.lib.mail.Envelope;
import com.itquasar.multiverse.lib.mail.util.Parser;
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
    public List<EmailContact> getBcc() {
        this.init();
        return envelope.getBcc();
    }

    @Override
    public List<EmailContact> getCc() {
        this.init();
        return envelope.getCc();
    }

    @Override
    public List<EmailContact> getFrom() {
        this.init();
        return envelope.getFrom();
    }

    @Override
    public List<EmailContact> getReplyTo() {
        this.init();
        return envelope.getReplyTo();
    }

    @Override
    public List<EmailContact> getTo() {
        this.init();
        return envelope.getTo();
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
