package com.itquasar.multiverse.mail.part;

import com.itquasar.multiverse.mail.util.Parser;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface Part<T> {

    ID getContentId();

    MimeType getMimeType();

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

    default boolean isMimeType(MimeTypes mimeType) {
        return Parser.isSameMime(this.getMimeType().getMimeType(), mimeType);
    }

    default boolean isMimeType(String mimeType) {
        return Parser.isSameMime(this.getMimeType().getMimeType(), mimeType);
    }

}
