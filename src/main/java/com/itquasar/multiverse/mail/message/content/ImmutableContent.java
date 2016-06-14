package com.itquasar.multiverse.mail.message.content;

import com.itquasar.multiverse.mail.part.Attachment;
import com.itquasar.multiverse.mail.part.HtmlPart;
import com.itquasar.multiverse.mail.part.Inline;
import com.itquasar.multiverse.mail.part.Part;
import com.itquasar.multiverse.mail.part.TextPart;
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

    private final TextPart tetxtPart;
    private final HtmlPart htmlPart;
    private final List<Inline<?>> htmlImages;
    private final List<Attachment<?>> attachments;

    public ImmutableContent() {
        this(null, null, null, null);
    }

    public ImmutableContent(String textContent) {
        this(new TextPart(textContent));
    }

    public ImmutableContent(TextPart textContent) {
        this(textContent, null, null);
    }

    public ImmutableContent(String text, String html) {
        this(new TextPart(text), new HtmlPart(html));
    }

    public ImmutableContent(TextPart textPart, HtmlPart htmlPart) {
        this(textPart, htmlPart, null, null);
    }

    public ImmutableContent(HtmlPart htmlContent, List<Inline<?>> htmlImages) {
        this(null, htmlContent, htmlImages);
    }

    public ImmutableContent(TextPart textContent, HtmlPart htmlContent,
            List<Inline<?>> htmlImages) {
        this(textContent, htmlContent, htmlImages, null);
    }

    public ImmutableContent(TextPart textContent, HtmlPart htmlContent,
            List<Inline<?>> htmlImages, List<Attachment<?>> attachments) {
        this.tetxtPart = FunctionUtils.defaultOnNull(textContent, Constants.EMPTY_TEXT_PART);
        this.htmlPart = FunctionUtils.defaultOnNull(htmlContent, Constants.EMPTY_HTML_PART);
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
    public TextPart getTextPart() {
        return tetxtPart;
    }

    /**
     *
     * @return The first text/html part
     */
    @Override
    public HtmlPart getHtmlPart() {
        return htmlPart;
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
