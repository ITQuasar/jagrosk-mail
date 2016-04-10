package com.itquasar.multiverse.mail.java;

import com.itquasar.multiverse.mail.message.content.Content;
import com.itquasar.multiverse.mail.message.ImmutableEmail;
import com.itquasar.multiverse.mail.message.envelope.Envelope;
import javax.mail.Message;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class JEmail extends ImmutableEmail {

    public JEmail(Envelope envelope, Content content) {
        super(envelope, content);
    }

    public JEmail(Message message) {
        super(message);
    }

}
