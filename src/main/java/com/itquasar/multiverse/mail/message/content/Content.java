package com.itquasar.multiverse.mail.message.content;

import com.itquasar.multiverse.mail.part.Attachment;
import com.itquasar.multiverse.mail.part.Inline;
import com.itquasar.multiverse.mail.part.Part;
import com.itquasar.multiverse.mail.util.Constants;
import com.itquasar.multiverse.mail.util.FunctionUtils;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface Content extends Constants {

    List<Attachment<?>> getAttachments();

    /**
     *
     * @return The first text/html part
     */
    Part<String> getHtmlPart();

    /**
     *
     * @return The content from the first text/html part
     */
    default String getHtmlContent() {
        return FunctionUtils.doubleDefaultOnNull(getHtmlPart(), () -> getHtmlPart().getContent(), EMPTY_STRING);
    }

    List<Inline<?>> getHtmlImages();

    /**
     *
     * @return The first text/plain part
     */
    Part<String> getTextPart();

    /**
     *
     * @return The content from the first text/plain part
     */
    default String getTextContent() {
        return FunctionUtils.doubleDefaultOnNull(getTextPart(), () -> getTextPart().getContent(), EMPTY_STRING);
    }

    /**
     *
     * @return {@code true} if html text part has content
     * ({@link Part#hasContent()}) and the string content is not empty
     * ({@link String#isEmpty()}).
     */
    default boolean hasTextHtml() {
        return getHtmlPart() != null
                && getHtmlPart().getContent() != null
                && !getHtmlPart().getContent().isEmpty();
    }

    default boolean hasImages() {
        return getHtmlImages() != null
                && !getHtmlImages().isEmpty();
    }

    /**
     *
     * @return {@code true} if plain text part has content
     * ({@link Part#hasContent()}) and the string content is not empty
     * ({@link String#isEmpty()}).
     */
    default boolean hasTextPlain() {
        return getTextPart() != null
                && getTextPart().getContent() != null
                && !getTextPart().getContent().isEmpty();
    }

}
