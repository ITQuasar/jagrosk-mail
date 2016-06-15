package com.itquasar.multiverse.mail.contact;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public abstract class AbstractRecipients implements Recipients {

    @Override
    public String toString() {
        return "Recipients{" + "to=" + getTo() + ", cc=" + getCc() + ", bcc=" + getBcc() + '}';
    }
}
