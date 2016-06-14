/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.contact;

import static com.itquasar.multiverse.mail.util.Constants.NO_ONE;
import com.itquasar.multiverse.mail.util.FunctionUtils;
import java.util.Set;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class MutableSenders extends AbstractSenders {

    private Contact sender;
    private Set<Contact> from;
    private Set<Contact> replyTo;

    public MutableSenders(Contact from) {
        this(FunctionUtils.toSet(from));
    }

    public MutableSenders(Set<Contact> from) {
        this(from, NO_ONES_SET);
    }

    public MutableSenders(Set<Contact> from, Set<Contact> replyTo) {
        this(NO_ONE, from, replyTo);
    }

    public MutableSenders(Contact sender, Set<Contact> from, Set<Contact> replyTo) {
        this.sender = sender;
        this.from = from;
        this.replyTo = replyTo;
    }

    @Override
    public Contact getSender() {
        return sender;
    }

    @Override
    public Set<Contact> getFrom() {
        return from;
    }

    @Override
    public Set<Contact> getReplyTo() {
        return replyTo;
    }

    public void setSender(Contact sender) {
        this.sender = sender;
    }

    public void setFrom(Set<Contact> from) {
        this.from = from;
    }

    public void setReplyTo(Set<Contact> replyTo) {
        this.replyTo = replyTo;
    }

}
