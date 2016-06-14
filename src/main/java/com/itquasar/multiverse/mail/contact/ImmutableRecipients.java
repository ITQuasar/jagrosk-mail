/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.contact;

import java.util.Collections;
import java.util.Set;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class ImmutableRecipients extends AbstractRecipients {

    private final Set<Contact> to;
    private final Set<Contact> cc;
    private final Set<Contact> bcc;

    public ImmutableRecipients(Set<Contact> to) {
        this(to, NO_ONES_SET);
    }

    public ImmutableRecipients(Set<Contact> to, Set<Contact> cc) {
        this(to, cc, NO_ONES_SET);
    }

    public ImmutableRecipients(Set<Contact> to, Set<Contact> cc, Set<Contact> bcc) {
        this.to = Collections.unmodifiableSet(to);
        this.cc = Collections.unmodifiableSet(cc);
        this.bcc = Collections.unmodifiableSet(bcc);
    }

    public Set<Contact> getTo() {
        return to;
    }

    public Set<Contact> getCc() {
        return cc;
    }

    public Set<Contact> getBcc() {
        return bcc;
    }

}
