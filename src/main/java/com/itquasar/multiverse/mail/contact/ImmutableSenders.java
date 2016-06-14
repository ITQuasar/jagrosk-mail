/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.contact;

import com.itquasar.multiverse.mail.util.FunctionUtils;
import java.util.Collections;
import java.util.Set;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class ImmutableSenders extends AbstractSenders {

    private final Contact sender;
    private final Set<Contact> from;
    private final Set<Contact> replyTo;

    public ImmutableSenders(Contact from) {
        this(FunctionUtils.toSet(from));
    }

    public ImmutableSenders(Set<Contact> from) {
        this(from, NO_ONES_SET);
    }

    public ImmutableSenders(Set<Contact> from, Set<Contact> replyTo) {
        this(NO_ONE, from, replyTo);
    }

    public ImmutableSenders(Contact sender, Set<Contact> from, Set<Contact> replyTo) {
        this.sender = sender;
        this.from = Collections.unmodifiableSet(from);
        this.replyTo = Collections.unmodifiableSet(replyTo);
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

}
