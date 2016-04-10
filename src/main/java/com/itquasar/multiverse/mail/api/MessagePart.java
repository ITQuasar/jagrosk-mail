package com.itquasar.multiverse.mail.api;

import com.itquasar.multiverse.mail.message.Email;
import com.itquasar.multiverse.mail.part.Disposition;
import com.itquasar.multiverse.mail.part.GenericPart;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class MessagePart extends GenericPart<Email> {

    public MessagePart(String name, Disposition disposition, String mimeType, Email content) {
        super(name, disposition, mimeType, content);
    }
}
