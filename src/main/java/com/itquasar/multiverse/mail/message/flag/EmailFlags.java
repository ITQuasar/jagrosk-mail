/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message.flag;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class EmailFlags implements Iterable<EmailFlag> {

    private final List<EmailFlag> flags;

    public EmailFlags() {
        this(null);
    }

    public EmailFlags(List<EmailFlag> flags) {
        this.flags = flags == null ? new LinkedList<>() : flags;
    }

    public EmailFlags set(EmailFlag flag) {
        this.flags.add(flag);
        return this;
    }

    public EmailFlags unset(EmailFlag flag) {
        this.flags.remove(flag);
        return this;
    }

    public EmailFlags clear() {
        this.flags.clear();
        return this;
    }

    @Override
    public Iterator<EmailFlag> iterator() {
        return this.flags.iterator();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.flags);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmailFlags other = (EmailFlags) obj;
        if (!Objects.equals(this.flags, other.flags)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EmailFlags{" + "flags=" + flags + '}';
    }

}
