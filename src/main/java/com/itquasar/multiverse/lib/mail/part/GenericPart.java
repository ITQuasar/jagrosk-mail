package com.itquasar.multiverse.lib.mail.part;

import com.itquasar.multiverse.lib.mail.util.Constants;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 * @param <T>
 */
public class GenericPart<T> implements Part<T> {

    private final String contentId;
    private final Disposition disposition;
    private final String name;
    private final String mimeType;
    private final T content;
    private final List<Part> parts;

    public GenericPart(String contentId, Disposition disposition, String name, String mimeType, T content, List<Part> parts) {
        this.contentId = contentId == null ? Constants.EMPTY_STRING : contentId;
        this.disposition = disposition;
        this.name = name == null ? Constants.EMPTY_STRING : name;
        this.mimeType = mimeType.toLowerCase();
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
    public String getContentId() {
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
    public String getMimeType() {
        return mimeType;
    }

    @Override
    public T getContent() {
        return content;
    }

    @Override
    public boolean hasContent() {
        return this.content != null;
    }

    @Override
    public List<Part> getParts() {
        return parts;
    }

    @Override
    public String toString() {
        return "GenericPart{" + "contentId=" + contentId + ", disposition=" + disposition + ", name=" + name + ", mimeType=" + mimeType + ", content=" + (content != null ? content.getClass().getCanonicalName() : "<<null>>") + '}';
    }

}
