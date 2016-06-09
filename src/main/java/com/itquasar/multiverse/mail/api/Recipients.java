/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.api;

import com.itquasar.multiverse.mail.util.Constants;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
// TODO: put this inside envelope
// TODO: use this on reply, replyAll and forward
public class Recipients implements Constants {

    public static Recipients copyTo(Envelope envelope) {
        return new Recipients(envelope.getTo());
    }

    public static Recipients copyToAndCc(Envelope envelope) {
        return new Recipients(envelope.getTo(), envelope.getCc());
    }

    public static Recipients copyToAndBcc(Envelope envelope) {
        return new Recipients(envelope.getTo(), NO_ONES, envelope.getBcc());
    }

    public static Recipients copyToAndCcAndBcc(Envelope envelope) {
        return new Recipients(envelope.getTo(), envelope.getCc(), envelope.getBcc());
    }

    public static Recipients copyCc(Envelope envelope) {
        return new Recipients(NO_ONES, envelope.getCc());
    }

    public static Recipients copyCcAndBcc(Envelope envelope) {
        return new Recipients(NO_ONES, envelope.getCc(), envelope.getBcc());
    }

    public static Recipients copyBcc(Envelope envelope) {
        return new Recipients(NO_ONES, NO_ONES, envelope.getBcc());
    }

    private final List<Contact> to;
    private final List<Contact> cc;
    private final List<Contact> bcc;

    public Recipients(List<Contact> to) {
        this(to, NO_ONES);
    }

    public Recipients(List<Contact> to, List<Contact> cc) {
        this(to, cc, NO_ONES);
    }

    public Recipients(List<Contact> to, List<Contact> cc, List<Contact> bcc) {
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
    }

    public List<Contact> getTo() {
        return to;
    }

    public List<Contact> getCc() {
        return cc;
    }

    public List<Contact> getBcc() {
        return bcc;
    }

    @Override
    public String toString() {
        return "Recipients{" + "to=" + to + ", cc=" + cc + ", bcc=" + bcc + '}';
    }

}
