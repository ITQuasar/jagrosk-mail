/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.api;

import com.itquasar.multiverse.mail.util.Constants;
import com.itquasar.multiverse.mail.util.FunctionUtils;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class Senders implements Constants {

    private final Contact sender;
    private final List<Contact> from;
    private final List<Contact> replyTo;

    public Senders(Contact from) {
        this(FunctionUtils.toList(from));
    }

    public Senders(List<Contact> from) {
        this(from, NO_ONES);
    }

    public Senders(List<Contact> from, List<Contact> replyTo) {
        this(NO_ONE, from, replyTo);
    }

    public Senders(Contact sender, List<Contact> from, List<Contact> replyTo) {
        this.sender = sender;
        this.from = from;
        this.replyTo = replyTo;
    }

    public Contact getSender() {
        return sender;
    }

    public List<Contact> getFrom() {
        return from;
    }

    public List<Contact> getReplyTo() {
        return replyTo;
    }

    @Override
    public String toString() {
        return "Senders{" + "sender=" + sender + ", from=" + from + ", replyTo=" + replyTo + '}';
    }

}
