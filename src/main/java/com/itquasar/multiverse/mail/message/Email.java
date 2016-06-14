/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message;

import com.itquasar.multiverse.mail.message.content.Content;
import com.itquasar.multiverse.mail.message.envelope.Envelope;
import com.itquasar.multiverse.mail.util.EmailUtils;
import javax.mail.Message;
import javax.mail.Session;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface Email {

    /**
     *
     * @return The message envelope.
     */
    Envelope getEnvelope();

    /**
     *
     * @return The message content.
     */
    Content getContent();

    default Message toMessage(Session session) {
        return EmailUtils.toMessage(this, session);
    }

}
