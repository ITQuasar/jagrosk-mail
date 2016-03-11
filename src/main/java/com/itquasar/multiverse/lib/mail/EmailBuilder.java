package com.itquasar.multiverse.lib.mail;

import com.itquasar.multiverse.lib.mail.part.Attachment;
import com.itquasar.multiverse.lib.mail.part.Inline;
import com.itquasar.multiverse.lib.mail.util.Constants;
import com.itquasar.multiverse.lib.mail.util.Utils;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class EmailBuilder {

    public static EmailBuilder newBuilder() {
        return new EmailBuilder();
    }

    private List<EmailContact> from = Constants.NO_ONES;
    private List<EmailContact> replyTo = Constants.NO_ONES;

    private List<EmailContact> to = Constants.NO_ONES;
    private List<EmailContact> cc = Constants.NO_ONES;
    private List<EmailContact> bcc = Constants.NO_ONES;

    private String subject = Constants.EMPTY_STRING;
    private String textContent = Constants.EMPTY_STRING;
    private String htmlContent = Constants.EMPTY_STRING;
    private List<Inline> htmlImages = Collections.emptyList();

    private List<Attachment> attachments = new LinkedList<>();

    private EmailBuilder() {
    }

    public EmailBuilder from(EmailContact... contacts) {
        this.from = Utils.safeArrayToList(contacts);
        return this;
    }

    public EmailBuilder replyTo(EmailContact... contacts) {
        this.replyTo = Utils.safeArrayToList(contacts);
        return this;
    }

    public EmailBuilder to(EmailContact... contacts) {
        this.to = Utils.safeArrayToList(contacts);
        return this;
    }

    public EmailBuilder cc(EmailContact... contacts) {
        this.cc = Utils.safeArrayToList(contacts);
        return this;
    }

    public EmailBuilder bcc(EmailContact... contacts) {
        this.bcc = Utils.safeArrayToList(contacts);
        return this;
    }

    public EmailBuilder subject(String subject) {
        this.subject = Utils.emptyOnNull(subject);
        return this;
    }

    public EmailBuilder content(TemplatedContent content) {
        this.subject(content.getSubject());
        this.textMessage(content.getTextContent());
        this.htmlMessage(content.getHtmlContent(), content.getHtmlImages());
        return this;
    }

    public EmailBuilder message(String textContent, String htmlContent, Attachment... images) {
        return this.message(textContent, htmlContent, Utils.safeArrayToList(images));
    }

    public EmailBuilder message(String textContent, String htmlContent, List<Attachment> images) {
        this.textMessage(htmlContent);
        this.htmlMessage(htmlContent, images);
        return this;
    }

    public EmailBuilder textMessage(String content) {
        this.textContent = Utils.emptyOnNull(content);
        return this;
    }

    public EmailBuilder htmlMessage(String content) {
        this.htmlContent = Utils.emptyOnNull(content);
        return this;
    }

    public EmailBuilder htmlMessage(String content, List<Attachment> images) {
        this.htmlMessage(content);
        this.htmlImages = Utils.defaultOnNull(htmlImages, new LinkedList<Inline>());
        return this;
    }

    public EmailBuilder htmlMessage(String content, Attachment... images) {
        return this.htmlMessage(content, Utils.safeArrayToList(images));
    }

    /**
     * Add any attachment to the attachments list.
     *
     * @param attachments
     * @return
     */
    public EmailBuilder attach(Attachment... attachments) {
        return this.attach(Utils.safeArrayToList(attachments));
    }

    public EmailBuilder attach(List<Attachment> attachments) {
        this.attachments.addAll(attachments);
        return this;
    }

    public Email build() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

}
