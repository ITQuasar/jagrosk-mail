/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message.envelope;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public abstract class AbstractEnvelope implements Envelope {

    @Override
    public String toString() {
        return "Envelope{" + getSender() + "#" + getSubject() + '}';
    }

}
