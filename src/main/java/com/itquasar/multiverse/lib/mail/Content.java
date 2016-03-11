package com.itquasar.multiverse.lib.mail;

import com.itquasar.multiverse.lib.mail.exception.EmailException;
import com.itquasar.multiverse.lib.mail.part.Part;
import com.itquasar.multiverse.lib.mail.util.Constants;
import com.itquasar.multiverse.lib.mail.util.Parser;
import com.itquasar.multiverse.lib.mail.util.Utils;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;

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

    public Content(Message message) {
        try {
            this.subject = Utils.emptyOnNull(message.getSubject());

            List<Part> parts = Parser.parseParts(message);

            Part<String> text = Constants.EMPTY_TEXT_PART;
            Part<String> html = Constants.EMPTY_HTML_PART;
            List<Part> images = new LinkedList<>();
            List<Part> attachs = new LinkedList<>();

            for (Part part : parts) {
                if (text.getContent().isEmpty() && part.getMimeType().startsWith("text/plain")) {
                    text = part;
                } else if (html.getContent().isEmpty()) {
                    if (part.getMimeType().startsWith("multipart")) {
                        List<Part> subParts = part.getParts();
                        if (subParts.get(0).getMimeType().startsWith("text/html")) {
                            html = subParts.remove(0);
                            images.addAll(subParts);
                        }
                    } else {
                        html = part;
                    }
                } else {
                    attachs.add(part);
                }
            }
            this.textContent = text;
            this.htmlContent = html;
            this.htmlImages = images;
            this.attachments = attachs;
        } catch (MessagingException | IOException ex) {
            Logger.getLogger(Content.class.getName()).log(Level.SEVERE, null, ex);
            throw new EmailException("Could not build content from Message", ex);
        }
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

    public Part<String> getTextContent() {
        return textContent;
    }

    public Part<String> getHtmlContent() {
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
