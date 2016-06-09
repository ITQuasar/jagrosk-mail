package com.itquasar.multiverse.mail.api.java;

import com.itquasar.multiverse.mail.api.Content;
import com.itquasar.multiverse.mail.api.Envelope;
import com.itquasar.multiverse.mail.message.ImmutableEmail;
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
