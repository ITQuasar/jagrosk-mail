/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.contact;

import java.util.Set;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public abstract class AbstractSenders implements Senders {

    @Override
    public abstract Set<Contact> getReplyTo();

    @Override
    public abstract Set<Contact> getFrom();

    @Override
    public String toString() {
        return "Senders{" + "sender=" + getSender() + ", from=" + getFrom() + ", replyTo=" + getReplyTo() + '}';
    }

}
