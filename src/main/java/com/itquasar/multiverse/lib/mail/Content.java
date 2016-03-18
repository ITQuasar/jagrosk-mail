package com.itquasar.multiverse.lib.mail;

import com.itquasar.multiverse.lib.mail.part.Part;
import com.itquasar.multiverse.lib.mail.part.SinglePart;
import com.itquasar.multiverse.lib.mail.util.Constants;
import com.itquasar.multiverse.lib.mail.util.Utils;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class Content {

    private final String subject;
    private final Part<String> textContent;
    private final Part<String> htmlContent;
    private final List<Part> htmlImages;
    private final List<Part> attachments;

    public Content(String subject, String textContent) {
        this(subject, new SinglePart(Part.Mime.TEXT_PLAIN, textContent));
    }

    public Content(String subject, Part<String> textContent) {
        this(subject, textContent, null, null);
    }

    public Content(String subject, Part<String> htmlContent, List<Part> htmlImages) {
        this(subject, null, htmlContent, htmlImages);
    }

    public Content(String subject, Part<String> textContent, Part<String> htmlContent, List<Part> htmlImages) {
        this(subject, textContent, htmlContent, htmlImages, null);
    }

    public Content(String subject, Part<String> textContent, Part<String> htmlContent, List<Part> htmlImages,
            List<Part> attachments) {
        this.subject = Utils.emptyOnNull(subject);
        this.textContent = Utils.defaultOnNull(textContent, Constants.EMPTY_TEXT_PART);
        this.htmlContent = Utils.defaultOnNull(htmlContent, Constants.EMPTY_HTML_PART);
        this.htmlImages = Utils.defaultOnNull(htmlImages, Constants.NO_PARTS);
        this.attachments = Utils.defaultOnNull(attachments, Constants.NO_PARTS);
    }

    public String getSubject() {
        return subject;
    }

    /**
     *
     * @return The content from the first text/plain part
     */
    public String getTextContent() {
        return textContent.getContent();
    }

    /**
     *
     * @return The first text/plain part
     */
    public Part<String> getTextPart() {
        return textContent;
    }

    /**
     *
     * @return The content from the first text/html part
     */
    public String getHtmlContent() {
        return htmlContent.getContent();
    }

    /**
     *
     * @return The first text/html part
     */
    public Part<String> getHtmlPart() {
        return htmlContent;
    }

    public List<Part> getHtmlImages() {
        return htmlImages;
    }

    public List<Part> getAttachments() {
        return attachments;
    }

    public boolean hasTextPlain() {
        return !this.textContent.getContent().isEmpty();
    }

    public boolean hasHtmlPlain() {
        return !this.htmlContent.getContent().isEmpty();
    }

    public boolean hasImages() {
        return !this.htmlImages.isEmpty();
    }

    @Override
    public String toString() {
        return "Content{" + "subject=" + subject + ", textContent=" + textContent + ", htmlContent=" + htmlContent + ", htmlImages=" + htmlImages + ", attachments=" + attachments + '}';
    }

}
