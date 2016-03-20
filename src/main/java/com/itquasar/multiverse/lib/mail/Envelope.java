/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.lib.mail;

import com.itquasar.multiverse.lib.mail.EmailContact;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface Envelope {

    List<EmailContact> getBcc();

    List<EmailContact> getCc();

    List<EmailContact> getFrom();

    List<EmailContact> getReplyTo();

    List<EmailContact> getTo();

    String getSubject();
}
