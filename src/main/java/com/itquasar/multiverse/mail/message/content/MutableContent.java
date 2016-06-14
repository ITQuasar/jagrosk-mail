/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message.content;

import com.itquasar.multiverse.mail.part.Attachment;
import com.itquasar.multiverse.mail.part.Inline;
import com.itquasar.multiverse.mail.part.MimeTypes;
import com.itquasar.multiverse.mail.part.Part;
import com.itquasar.multiverse.mail.part.SinglePart;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class MutableContent extends AbstractContent {

    private Part<String> textPart;
    private Part<String> htmlPart;
    private List<Inline<?>> htmlImages;
    private List<Attachment<?>> attachments;

    public MutableContent() {
        this(null, null, null, null);
    }

    public MutableContent(String textContent) {
        this(new SinglePart(MimeTypes.TEXT_PLAIN, textContent));
    }

    public MutableContent(Part<String> textContent) {
        this(textContent, null, null);
    }

    public MutableContent(String text, String html) {
        this(new SinglePart<>(MimeTypes.TEXT_PLAIN, text),
                new SinglePart<>(MimeTypes.TEXT_HTML, html));
    }

    public MutableContent(Part<String> textPart, Part<String> htmlPart) {
        this(textPart, htmlPart, null, null);
    }

    public MutableContent(Part<String> htmlContent, List<Inline<?>> htmlImages) {
        this(null, htmlContent, htmlImages);
    }

    public MutableContent(Part<String> textContent, Part<String> htmlContent,
            List<Inline<?>> htmlImages) {
        this(textContent, htmlContent, htmlImages, null);
    }

    public MutableContent(Part<String> textPart, Part<String> htmlPart, List<Inline<?>> htmlImages, List<Attachment<?>> attachments) {
        this.textPart = textPart == null ? EMPTY_TEXT_PART : textPart;
        this.htmlPart = htmlPart == null ? EMPTY_HTML_PART : htmlPart;
        this.htmlImages = htmlImages == null ? new LinkedList<>() : htmlImages;
        this.attachments = attachments == null ? new LinkedList<>() : attachments;
    }

    @Override
    public Part<String> getTextPart() {
        return textPart;
    }

    @Override
    public Part<String> getHtmlPart() {
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

    public void setTextPart(Part<String> textPart) {
        this.textPart = textPart;
    }

    public void setHtmlPart(Part<String> htmlPart) {
        this.htmlPart = htmlPart;
    }

    public void setHtmlImages(List<Inline<?>> htmlImages) {
        this.htmlImages = htmlImages;
    }

    public void setAttachments(List<Attachment<?>> attachments) {
        this.attachments = attachments;
    }

}
