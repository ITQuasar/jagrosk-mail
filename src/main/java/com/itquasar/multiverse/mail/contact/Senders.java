/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.contact;

import com.itquasar.multiverse.mail.util.Constants;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface Senders extends Constants {

    Contact getSender();

    Iterable<Contact> getFrom();

    Iterable<Contact> getReplyTo();

}
