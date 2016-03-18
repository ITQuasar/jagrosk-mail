package com.itquasar.multiverse.lib.mail.part;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class SinglePart<T> extends GenericPart<T> {

    public SinglePart(String contentId, Disposition disposition, String name, String mimeType, T content) {
        super(contentId, disposition, name, mimeType, content);
    }

    public SinglePart(String name, Disposition disposition, String mimeType, T content) {
        super(name, disposition, mimeType, content);
    }

    public SinglePart(String mimeType, T content) {
        super(mimeType, content);
    }

    public SinglePart(Part.Mime mimeType, T content) {
        this(mimeType.getMimeType(), content);
    }

}
