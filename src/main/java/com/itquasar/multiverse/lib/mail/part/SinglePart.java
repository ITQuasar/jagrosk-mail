package com.itquasar.multiverse.lib.mail.part;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class SinglePart<T> extends GenericPart<T> {

    public SinglePart(Part.Mime mimeType, T content) {
        this(mimeType.getMimeType(), content);
    }

    public SinglePart(String mimeType, T content) {
        super(mimeType, content);
    }

}
