/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.contact;

import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class ImmutableRecipients implements Recipients {

    private final List<Contact> to;
    private final List<Contact> cc;
    private final List<Contact> bcc;

    public ImmutableRecipients(List<Contact> to) {
        this(to, NO_ONES);
    }

    public ImmutableRecipients(List<Contact> to, List<Contact> cc) {
        this(to, cc, NO_ONES);
    }

    public ImmutableRecipients(List<Contact> to, List<Contact> cc, List<Contact> bcc) {
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
