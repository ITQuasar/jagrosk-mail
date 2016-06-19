/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message.flag;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class EmailFlags implements Iterable<EmailFlag> {

    private final Set<EmailFlag> flags;

    public EmailFlags() {
        this(null);
    }

    public EmailFlags(Set<EmailFlag> flags) {
        this.flags = flags == null ? new TreeSet<>() : flags;
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
        return "EmailFlags{" + flagsToString() + '}';
    }

    private String flagsToString() {
        Iterator<EmailFlag> iterator = iterator();
        StringBuilder buffy = new StringBuilder();
        buffy.append((iterator.hasNext() ? iterator.next() : ""));
        while (iterator.hasNext()) {
            buffy.append(",").append(iterator.next());
        }
        return buffy.toString();
    }

}
