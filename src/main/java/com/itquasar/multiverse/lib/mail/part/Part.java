package com.itquasar.multiverse.lib.mail.part;

import com.itquasar.multiverse.lib.mail.util.Parser;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface Part<T> {

    enum Disposition {

        INLINE, ATTACHMENT, NONE;

        public static Disposition evaluate(String disposition) {
            if (disposition != null) {
                return Disposition.valueOf(disposition.toUpperCase());
            }
            return NONE;
        }

        public String value() {
            return this.name().toLowerCase();
        }
    }

    enum Mime {
        TEXT("text/*"),
        TEXT_PLAIN("text/plain"),
        TEXT_HTML("text/html"),
        MESSAGE_RC822("message/rfc822"),
        IMAGE("image/*"),
        MULTIPART("multipart/*"),
        MULTIPART_ALTERNATIVE("multipart/alternative"),
        MULTIPART_MIXED("multipart/mixed"),
        MULTIPART_RELATED("multipart/related");

        private final String mimeType;

        private Mime(String mimeType) {
            this.mimeType = mimeType;
        }

        public String getMimeType() {
            return mimeType;
        }

    }

    String getContentId();

    String getMimeType();

    String getName();

    T getContent();

    Disposition getDisposition();

    default boolean hasContent() {
        return this.getContent() != null;
    }

    default boolean isMultipart() {
        return !this.getParts().isEmpty();
    }

    List<Part<?>> getParts();

    default boolean isMimeType(Part.Mime mimeType) {
        return Parser.isSameMime(this.getMimeType(), mimeType);
    }

    default boolean isMimeType(String mimeType) {
        return Parser.isSameMime(this.getMimeType(), mimeType);
    }

}
