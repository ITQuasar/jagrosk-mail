package com.itquasar.multiverse.lib.mail.content;

import com.itquasar.multiverse.lib.mail.Content;
import com.itquasar.multiverse.lib.mail.part.MimeTypes;
import com.itquasar.multiverse.lib.mail.part.Part;
import com.itquasar.multiverse.lib.mail.part.SinglePart;
import com.itquasar.multiverse.lib.mail.util.Constants;
import com.itquasar.multiverse.lib.mail.util.FunctionUtils;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class ImmutableContent implements Content {

    private final Part<String> textContent;
    private final Part<String> htmlContent;
    private final List<Part> htmlImages;
    private final List<Part> attachments;

    public ImmutableContent(String textContent) {
        this(new SinglePart(MimeTypes.TEXT_PLAIN, textContent));
    }

    public ImmutableContent(Part<String> textContent) {
        this(textContent, null, null);
    }

    public ImmutableContent(Part<String> htmlContent, List<Part> htmlImages) {
        this(null, htmlContent, htmlImages);
    }

    public ImmutableContent(Part<String> textContent, Part<String> htmlContent, List<Part> htmlImages) {
        this(textContent, htmlContent, htmlImages, null);
    }

    public ImmutableContent(Part<String> textContent, Part<String> htmlContent, List<Part> htmlImages,
            List<Part> attachments) {
        this.textContent = FunctionUtils.defaultOnNull(textContent, Constants.EMPTY_TEXT_PART);
        this.htmlContent = FunctionUtils.defaultOnNull(htmlContent, Constants.EMPTY_HTML_PART);
        this.htmlImages = FunctionUtils.defaultOnNull(htmlImages, Constants.NO_PARTS);
        this.attachments = FunctionUtils.defaultOnNull(attachments, Constants.NO_PARTS);
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
    public List<Part> getHtmlImages() {
        return htmlImages;
    }

    @Override
    public List<Part> getAttachments() {
        return attachments;
    }

    @Override
    public boolean hasTextPlain() {
        return this.textContent.hasContent()
                && !this.textContent.getContent().isEmpty();
    }

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
