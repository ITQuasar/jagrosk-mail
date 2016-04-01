package com.itquasar.multiverse.lib.mail;

import com.itquasar.multiverse.lib.mail.part.Disposition;
import com.itquasar.multiverse.lib.mail.part.GenericPart;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class MessagePart extends GenericPart<Email> {

    public MessagePart(String name, Disposition disposition, String mimeType, Email content) {
        super(name, disposition, mimeType, content);
    }
}
