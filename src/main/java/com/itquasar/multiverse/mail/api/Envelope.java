/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.api;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface Envelope {

    Contact getSender();

    List<Contact> getFrom();

    List<Contact> getReplyTo();

    List<Contact> getTo();

    List<Contact> getCc();

    List<Contact> getBcc();

    String getSubject();

    Optional<Instant> getReceivedOn();

    default Optional<ZonedDateTime> getReceivedOn(ZoneId zoneId) {
        return getReceivedOn().map((i) -> i.atZone(zoneId));
    }

    default Optional<ZonedDateTime> getReceivedOnOnSystemZoneId() {
        return getReceivedOn().map((i) -> i.atZone(ZoneId.systemDefault()));
    }

}
