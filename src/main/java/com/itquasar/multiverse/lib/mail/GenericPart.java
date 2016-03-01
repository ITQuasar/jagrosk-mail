package com.itquasar.multiverse.lib.mail;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 * @param <T>
 */
public class GenericPart<T> implements Part<T> {

    public static final GenericPart<String> EMPTY_PART = new GenericPart<>("", Disposition.NONE, "", "");

    private final String contentId;
    private final Disposition disposition;
    private final String name;
    private final String mimeType;
    private final T content;

    public GenericPart(String contentId, Disposition disposition, String name, String mimeType, T content) {
        this.contentId = contentId == null ? "" : contentId;
        this.disposition = disposition;
        this.name = name == null ? "" : name;
        this.mimeType = mimeType.toLowerCase();
        this.content = content;
    }

    public GenericPart(String contentId, String disposition, String name, String mimeType, T content) {
        this(contentId, Disposition.evaluate(disposition), name, mimeType, content);
    }

    /**
     * Uses name as contentId;
     *
     * @param name
     * @param disposition
     * @param mimeType
     * @param content
     */
    public GenericPart(String name, Disposition disposition, String mimeType, T content) {
        this(name, disposition, name, mimeType, content);
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
    public String toString() {
        return "GenericPart{" + "contentId=" + contentId + ", disposition=" + disposition + ", name=" + name + ", mimeType=" + mimeType + ", content=" + content + '}';
    }

}
