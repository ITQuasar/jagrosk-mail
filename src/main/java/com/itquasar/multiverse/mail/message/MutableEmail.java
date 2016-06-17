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
import com.itquasar.multiverse.mail.message.flag.EmailFlag;
import java.util.HashSet;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class MutableEmail implements BuildedEmail {

    private Envelope envelope;
    private Content content;
    private Iterable<EmailFlag> flags;

    public MutableEmail() {
        this(null, null);
    }

    public MutableEmail(Envelope envelope, Content content) {
        this(envelope, content, null);
    }

    public MutableEmail(Envelope envelope, Content content, Iterable<EmailFlag> flags) {
        this.envelope = envelope == null ? new MutableEnvelope() : envelope;
        this.content = content == null ? new MutableContent() : content;
        this.flags = flags == null ? new HashSet<>() : flags;
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
    public Iterable<EmailFlag> getFlags() {
        return flags;
    }

    public void setFlags(Iterable<EmailFlag> flags) {
        this.flags = flags;
    }

}
