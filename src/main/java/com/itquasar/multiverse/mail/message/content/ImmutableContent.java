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
public class ImmutableContent extends AbstractContent {

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

    public ImmutableContent(String text, String html) {
        this(new SinglePart<>(MimeTypes.TEXT_PLAIN, text),
                new SinglePart<>(MimeTypes.TEXT_HTML, html));
    }

    public ImmutableContent(Part<String> textPart, Part<String> htmlPart) {
        this(textPart, htmlPart, null, null);
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
     * @return The first text/plain part
     */
    @Override
    public Part<String> getTextPart() {
        return textContent;
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

}
