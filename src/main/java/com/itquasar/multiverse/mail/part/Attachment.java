package com.itquasar.multiverse.mail.part;

import java.util.Collections;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 * @param <T>
 */
public class Attachment<T> extends GenericPart<T> {

    public static final Disposition ATTACHMENT_DISPOSITION = Disposition.ATTACHMENT;

    public Attachment(String contentId, String name, String mimeType, T content) {
        super(contentId, ATTACHMENT_DISPOSITION, name, mimeType, content, Collections.emptyList());
    }

    public Attachment(String name, String mimeType, T content) {
        super(name, ATTACHMENT_DISPOSITION, mimeType, content);
    }

}
