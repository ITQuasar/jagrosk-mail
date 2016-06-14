/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message;

import com.itquasar.multiverse.mail.message.content.Content;
import com.itquasar.multiverse.mail.message.content.MutableContent;
import com.itquasar.multiverse.mail.message.envelope.Envelope;
import com.itquasar.multiverse.mail.message.envelope.MutableEnvelope;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class MutableEmail implements Email {

    private Envelope envelope;
    private Content content;

    public MutableEmail() {
        this(null, null);
    }

    public MutableEmail(Envelope envelope, Content content) {
        this.envelope = envelope == null ? new MutableEnvelope() : envelope;
        this.content = content == null ? new MutableContent() : content;
    }

    @Override
    public Envelope getEnvelope() {
        return envelope;
    }

    @Override
    public Content getContent() {
        return content;
    }

}
