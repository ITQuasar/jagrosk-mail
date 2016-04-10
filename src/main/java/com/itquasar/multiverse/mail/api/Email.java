/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.api;

import com.itquasar.multiverse.mail.message.ImmutableEmail;
import com.itquasar.multiverse.mail.message.ImmutableEmail;
import javax.mail.Message;
import javax.mail.Session;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface Email {

    /**
     * Create an email using this message, the content supplied adding all
     * attachments to it, {@code from} supplied as from ad {@code replyTo} and
     * {@code to} as recipients
     *
     * @param from
     * @param content
     * @param to
     * @return A new ImmutableEmail instance.
     */
    // FIXME: too much side effects
    ImmutableEmail forward(Contact from, Content content, Contact... to);

    /**
     *
     * @return The message content.
     */
    Content getContent();

    /**
     *
     * @return The message envelope.
     */
    Envelope getEnvelope();

    /**
     *
     * @return The size of wrapped message or -1 when no message wrapped.
     */
    int getSize();

    /**
     * Create an email using this message, the content supplied and {@code from}
     * supplied as from ad {@code replyTo}
     *
     * @param from
     * @param content
     * @return A new ImmutableEmail instance.
     */
    // FIXME: too much side effects
    ImmutableEmail reply(Contact from, Content content);

    Message toMessage(Session session);

}
