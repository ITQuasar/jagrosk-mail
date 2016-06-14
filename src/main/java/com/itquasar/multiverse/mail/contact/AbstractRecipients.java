package com.itquasar.multiverse.mail.contact;

import java.util.Set;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public abstract class AbstractRecipients implements Recipients {

    @Override
    public abstract Set<Contact> getTo();

    @Override
    public abstract Set<Contact> getCc();

    @Override
    public abstract Set<Contact> getBcc();

    @Override
    public String toString() {
        return "Recipients{" + "to=" + getTo() + ", cc=" + getCc() + ", bcc=" + getBcc() + '}';
    }
}
