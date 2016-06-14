/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message.envelope;

import com.itquasar.multiverse.mail.contact.Contact;
import com.itquasar.multiverse.mail.contact.Recipients;
import com.itquasar.multiverse.mail.contact.Senders;
import com.itquasar.multiverse.mail.util.Constants;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface Envelope extends Constants {

    Senders getSenders();

    Recipients getRecipients();

    default Contact getSender() {
        return getSenders().getSender();
    }

    default Iterable<Contact> getFrom() {
        return getSenders().getFrom();
    }

    default Iterable<Contact> getReplyTo() {
        return getSenders().getReplyTo();
    }

    default Iterable<Contact> getTo() {
        return getRecipients().getTo();
    }

    default Iterable<Contact> getCc() {
        return getRecipients().getCc();
    }

    default Iterable<Contact> getBcc() {
        return getRecipients().getBcc();
    }

    String getSubject();

    Optional<Instant> getReceivedOn();

    default Optional<ZonedDateTime> getReceivedOn(ZoneId zoneId) {
        return getReceivedOn().map((i) -> i.atZone(zoneId));
    }

    default Optional<ZonedDateTime> getReceivedOnOnSystemZoneId() {
        return getReceivedOn().map((i) -> i.atZone(ZoneId.systemDefault()));
    }

}
