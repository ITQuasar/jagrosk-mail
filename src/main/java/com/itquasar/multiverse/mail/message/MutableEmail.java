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
import com.itquasar.multiverse.mail.message.flag.EmailFlags;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class MutableEmail implements BuildedEmail {

    private Envelope envelope;
    private Content content;
    private EmailFlags flags;

    public MutableEmail() {
        this(null, null);
    }

    public MutableEmail(Envelope envelope, Content content) {
        this(envelope, content, null);
    }

    public MutableEmail(Envelope envelope, Content content, EmailFlags flags) {
        this.envelope = envelope == null ? new MutableEnvelope() : envelope;
        this.content = content == null ? new MutableContent() : content;
        this.flags = flags == null ? new EmailFlags() : flags;
    }

    @Override
    public Envelope getEnvelope() {
        return envelope;
    }

    @Override
    public Content getContent() {
        return content;
    }

    @Override
    public EmailFlags getFlags() {
        return flags;
    }

}
