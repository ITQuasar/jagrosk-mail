/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.contact;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class MutableRecipients extends AbstractRecipients {

    private Set<Contact> to;
    private Set<Contact> cc;
    private Set<Contact> bcc;

    public MutableRecipients(Set<Contact> to) {
        this(to, new HashSet<>());
    }

    public MutableRecipients(Set<Contact> to, Set<Contact> cc) {
        this(to, cc, new HashSet<>());
    }

    public MutableRecipients(Set<Contact> to, Set<Contact> cc, Set<Contact> bcc) {
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
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

    public void setTo(Set<Contact> to) {
        this.to = to;
    }

    public void setCc(Set<Contact> cc) {
        this.cc = cc;
    }

    public void setBcc(Set<Contact> bcc) {
        this.bcc = bcc;
    }

}
