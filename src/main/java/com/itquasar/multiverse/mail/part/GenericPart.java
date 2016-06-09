package com.itquasar.multiverse.mail.part;

import com.itquasar.multiverse.mail.util.Constants;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 * @param <T>
 */
public class GenericPart<T> implements Part<T> {

    private final ID contentId;
    private final Disposition disposition;
    private final String name;
    private final MimeType mimeType;
    private final T content;
    private final List<Part<?>> parts;

    public GenericPart(String contentId, Disposition disposition, String name, String mimeType, T content, List<Part<?>> parts) {
        this.contentId = new ID(contentId == null ? Constants.EMPTY_STRING : contentId);
        this.disposition = disposition;
        this.name = name == null ? Constants.EMPTY_STRING : name;
        this.mimeType = MimeType.build(mimeType.toLowerCase());
        this.content = content;
        this.parts = parts == null ? Collections.emptyList() : parts;
    }

    public GenericPart(String contentId, Disposition disposition, String name, String mimeType, T content) {
        this(contentId, disposition, name, mimeType, content, Collections.emptyList());
    }

    public GenericPart(String name, Disposition disposition, String mimeType, T content) {
        this(name, disposition, name, mimeType, content, null);
    }

    public GenericPart(String mimeType, T content) {
        this(Constants.EMPTY_STRING, Disposition.NONE, mimeType, content);
    }

    @Override
    public ID getContentId() {
        return contentId;
    }

    @Override
    public Disposition getDisposition() {
        return disposition;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public MimeType getMimeType() {
        return mimeType;
    }

    @Override
    public T getContent() {
        return content;
    }

    @Override
    public List<Part<?>> getParts() {
        return parts;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()
                + "#" + getContentId()
                + "[" + getDisposition() + "]"
                + ":" + getName() + ":" + getMimeType()
                + "=" + (content != null ? content.getClass().getCanonicalName() : "<<null>>");
    }

}
