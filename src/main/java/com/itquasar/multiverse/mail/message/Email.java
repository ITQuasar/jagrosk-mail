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

    /**
     *
     * @return The size of wrapped message or -1 when no message wrapped.
     */
    default int getSize() {
        return -1;
    }

//    /**
//     * Create an email using this message, the content supplied and {@code from}
//     * supplied as from ad {@code replyTo}
//     *
//     * @param from
//     * @param content
//     * @return A new ImmutableEmail instance.
//     */
//    Email reply(Contact from, Content content);
//
//    Email replyAll(Contact from, Content content);
//
//    default Email reply(Contact from, Content content, boolean replyAll) {
//        return replyAll ? replyAll(from, content) : reply(from, content);
//    }
//     /**
//     * Create an email using this message, the content supplied adding all
//     * attachments to it, {@code from} supplied as from ad {@code replyTo} and
//     * {@code to} as recipients
//     *
//     * @param from
//     * @param content
//     * @param to
//     * @return A new ImmutableEmail instance.
//     */
//    Email forward(Contact from, Content content, Contact... to);
    default Message toMessage(Session session) {
        return EmailUtils.toMessage(this, session);
    }

}
