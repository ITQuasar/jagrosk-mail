/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.api;

import java.util.Date;
import java.util.List;

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

    Date getReceivedOn();
}
