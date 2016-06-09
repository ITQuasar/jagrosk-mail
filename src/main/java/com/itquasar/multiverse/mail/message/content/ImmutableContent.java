package com.itquasar.multiverse.mail.message.content;

import com.itquasar.multiverse.mail.part.Attachment;
import com.itquasar.multiverse.mail.part.Inline;
import com.itquasar.multiverse.mail.part.MimeTypes;
import com.itquasar.multiverse.mail.part.Part;
import com.itquasar.multiverse.mail.part.SinglePart;
import com.itquasar.multiverse.mail.util.Constants;
import com.itquasar.multiverse.mail.util.FunctionUtils;
import java.util.Collections;
import java.util.List;

/**
 * This class assures immutable content on email messages at best effort basis.
 *
 * All fields are final and immutable. But as {@link Part} has a generic
 * content, if that content is mutable, there is nothing that can be done here
 * to prevent mutation on that part content.
 *
 * All other cases should remain immutable.
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class ImmutableContent implements Content {

    private final Part<String> textContent;
    private final Part<String> htmlContent;
    private final List<Inline<?>> htmlImages;
    private final List<Attachment<?>> attachments;

    public ImmutableContent(String textContent) {
        this(new SinglePart(MimeTypes.TEXT_PLAIN, textContent));
    }

    public ImmutableContent(Part<String> textContent) {
        this(textContent, null, null);
    }

    public ImmutableContent(Part<String> htmlContent, List<Inline<?>> htmlImages) {
        this(null, htmlContent, htmlImages);
    }

    public ImmutableContent(Part<String> textContent, Part<String> htmlContent,
            List<Inline<?>> htmlImages) {
        this(textContent, htmlContent, htmlImages, null);
    }

    public ImmutableContent(Part<String> textContent, Part<String> htmlContent,
            List<Inline<?>> htmlImages, List<Attachment<?>> attachments) {
        this.textContent = FunctionUtils.defaultOnNull(textContent, Constants.EMPTY_TEXT_PART);
        this.htmlContent = FunctionUtils.defaultOnNull(htmlContent, Constants.EMPTY_HTML_PART);
        this.htmlImages = Collections.unmodifiableList(
                FunctionUtils.defaultOnNull(htmlImages, Constants.NO_INLINES)
        );
        this.attachments = Collections.unmodifiableList(
                FunctionUtils.defaultOnNull(attachments, Constants.NO_ATTACHMENTS)
        );
    }

    /**
     *
     * @return The content from the first text/plain part
     */
    @Override
    public String getTextContent() {
        return textContent.getContent();
    }

    /**
     *
     * @return The first text/plain part
     */
    @Override
    public Part<String> getTextPart() {
        return textContent;
    }

    /**
     *
     * @return The content from the first text/html part
     */
    @Override
    public String getHtmlContent() {
        return htmlContent.getContent();
    }

    /**
     *
     * @return The first text/html part
     */
    @Override
    public Part<String> getHtmlPart() {
        return htmlContent;
    }

    @Override
    public List<Inline<?>> getHtmlImages() {
        return htmlImages;
    }

    @Override
    public List<Attachment<?>> getAttachments() {
        return attachments;
    }

    /**
     *
     * @return {@code true} if plain text part has content
     * ({@link Part#hasContent()}) and the string content is not empty
     * ({@link String#isEmpty()}).
     */
    @Override
    public boolean hasTextPlain() {
        return this.textContent.hasContent()
                && !this.textContent.getContent().isEmpty();
    }

    /**
     *
     * @return {@code true} if html text part has content
     * ({@link Part#hasContent()}) and the string content is not empty
     * ({@link String#isEmpty()}).
     */
    @Override
    public boolean hasTextHtml() {
        return this.htmlContent.hasContent()
                && !this.htmlContent.getContent().isEmpty();
    }

    @Override
    public boolean hasImages() {
        return !this.htmlImages.isEmpty();
    }

    @Override
    public String toString() {
        return "Content{" + "textContent=" + textContent + ", htmlContent=" + htmlContent + ", htmlImages=" + htmlImages + ", attachments=" + attachments + '}';
    }

}
