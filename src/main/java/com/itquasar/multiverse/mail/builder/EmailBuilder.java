package com.itquasar.multiverse.mail.builder;

import com.itquasar.multiverse.mail.contact.Contact;
import com.itquasar.multiverse.mail.message.Email;
import com.itquasar.multiverse.mail.message.ImmutableEmail;
import com.itquasar.multiverse.mail.message.TemplatedSubjectAndContent;
import com.itquasar.multiverse.mail.message.content.ImmutableContent;
import com.itquasar.multiverse.mail.message.envelope.ImmutableEnvelope;
import com.itquasar.multiverse.mail.message.flag.EmailFlag;
import com.itquasar.multiverse.mail.part.Attachment;
import com.itquasar.multiverse.mail.part.HtmlPart;
import com.itquasar.multiverse.mail.part.Inline;
import com.itquasar.multiverse.mail.part.TextPart;
import com.itquasar.multiverse.mail.util.Constants;
import com.itquasar.multiverse.mail.util.FunctionUtils;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This class builds an Email in a fluid manner. After setting all data as you
 * wish, a call to {@link #build()} will return an {@link ImmutableEmail}.
 *
 * All non setted fields will receive empty values (list or string).
 *
 * To get a instance of this builder class use {@link #newBuilder()}.
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class EmailBuilder {

    /**
     *
     * @return A new builder instance with all e-mail fields empty.
     */
    public static EmailBuilder newBuilder() {
        return new EmailBuilder();
    }

    private Set<Contact> from = Constants.NO_ONES_SET;
    private Set<Contact> replyTo = Constants.NO_ONES_SET;

    private Set<Contact> to = Constants.NO_ONES_SET;
    private Set<Contact> cc = Constants.NO_ONES_SET;
    private Set<Contact> bcc = Constants.NO_ONES_SET;

    private String subject = Constants.EMPTY_STRING;
    private String textContent = Constants.EMPTY_STRING;
    private String htmlContent = Constants.EMPTY_STRING;
    private List<Inline<?>> htmlImages = Collections.emptyList();

    private Set<EmailFlag> flags = new HashSet<>();

    private List<Attachment<?>> attachments = new LinkedList<>();

    private EmailBuilder() {
    }

    /**
     *
     * @param contacts {@code from} field of message.
     * @return The same builder instance.
     */
    public EmailBuilder from(Contact... contacts) {
        this.from = FunctionUtils.safeArrayToSet(contacts);
        return this;
    }

    /**
     *
     * @param addresses Email addresses.
     * @return The same builder instance.
     */
    public EmailBuilder from(String... addresses) {
        this.from = Contact.fromStrings(addresses);
        return this;
    }

    /**
     *
     * @param contacts {@code reply to} field of message.
     * @return The same builder instance.
     */
    public EmailBuilder replyTo(Contact... contacts) {
        this.replyTo = FunctionUtils.safeArrayToSet(contacts);
        return this;
    }

    /**
     *
     * @param addresses {@code reply to} field of message.
     * @return The same builder instance.
     */
    public EmailBuilder replyTo(String... addresses) {
        this.replyTo = Contact.fromStrings(addresses);
        return this;
    }

    /**
     *
     * @param contacts {@code to} field of message..
     * @return The same builder instance.
     */
    public EmailBuilder to(Contact... contacts) {
        this.to = FunctionUtils.safeArrayToSet(contacts);
        return this;
    }

    /**
     *
     * @param addresses {@code to} field of message..
     * @return The same builder instance.
     */
    public EmailBuilder to(String... addresses) {
        this.to = Contact.fromStrings(addresses);
        return this;
    }

    /**
     *
     * @param contacts {@code cc} field of message.
     * @return The same builder instance.
     */
    public EmailBuilder cc(Contact... contacts) {
        this.cc = FunctionUtils.safeArrayToSet(contacts);
        return this;
    }

    /**
     *
     * @param addresses {@code cc} field of message.
     * @return The same builder instance.
     */
    public EmailBuilder cc(String... addresses) {
        this.cc = Contact.fromStrings(addresses);
        return this;
    }

    /**
     *
     * @param contacts {@code bcc} field of message.
     * @return The same builder instance.
     */
    public EmailBuilder bcc(Contact... contacts) {
        this.bcc = FunctionUtils.safeArrayToSet(contacts);
        return this;
    }

    /**
     *
     * @param addresses {@code bcc} field of message.
     * @return The same builder instance.
     */
    public EmailBuilder bcc(String... addresses) {
        this.bcc = Contact.fromStrings(addresses);
        return this;
    }

    /**
     *
     * @param subject Subject of message.
     * @return The same builder instance.
     */
    public EmailBuilder subject(String subject) {
        this.subject = FunctionUtils.emptyOnNull(subject);
        return this;
    }

    /**
     * Sets the content using the given {@link TemplatedSubjectAndContent}, that
     * set subject, text and html message, according to implementation;
     *
     * @param content
     * @return The same builder instance.
     */
    public EmailBuilder content(TemplatedSubjectAndContent content) {
        this.subject(content.getSubject());
        this.textMessage(content.getTextContent());
        this.htmlMessage(content.getHtmlContent(), content.getHtmlImages());
        return this;
    }

    /**
     * Sets all content of message at once.
     *
     * @param textContent Plain text content.
     * @param htmlContent Html text content.
     * @param images Inlined images of message.
     * @return The same builder instance.
     */
    public EmailBuilder message(String textContent, String htmlContent, Inline<?>... images) {
        return this.message(textContent, htmlContent, FunctionUtils.safeArrayToList(images));
    }

    /**
     * Sets all content of message at once.
     *
     * @param textContent Plain text content.
     * @param htmlContent Html text content.
     * @param images Inlined images of message.
     * @return The same builder instance.
     */
    public EmailBuilder message(String textContent, String htmlContent, List<Inline<?>> images) {
        this.textMessage(htmlContent);
        this.htmlMessage(htmlContent, images);
        return this;
    }

    /**
     *
     * @param content Plain text content.
     * @return The same builder instance.
     */
    public EmailBuilder textMessage(String content) {
        this.textContent = FunctionUtils.emptyOnNull(content);
        return this;
    }

    /**
     *
     * @param content Html text content.
     * @return The same builder instance.
     */
    public EmailBuilder htmlMessage(String content) {
        this.htmlContent = FunctionUtils.emptyOnNull(content);
        return this;
    }

    /**
     *
     * @param content Html text content.
     * @param images Inlined images of message.
     * @return The same builder instance.
     */
    public EmailBuilder htmlMessage(String content, List<Inline<?>> images) {
        this.htmlMessage(content);
        this.htmlImages = FunctionUtils.defaultOnNull(htmlImages, new LinkedList<Inline<?>>());
        return this;
    }

    /**
     *
     * @param content Html text content.
     * @param images Inlined images of message.
     * @return The same builder instance.
     */
    public EmailBuilder htmlMessage(String content, Inline<?>... images) {
        return this.htmlMessage(content, FunctionUtils.safeArrayToList(images));
    }

    /**
     * Attachment something to the message.
     *
     * @param attachments
     * @return The same builder instance.
     */
    public EmailBuilder attach(Attachment<?>... attachments) {
        return this.attach(FunctionUtils.safeArrayToList(attachments));
    }

    /**
     * Attachment something to the message.
     *
     * @param attachments
     * @return The same builder instance.
     */
    public EmailBuilder attach(List<Attachment<?>> attachments) {
        this.attachments.addAll(attachments);
        return this;
    }

    /**
     * Add a flag to email.
     *
     * @param flag
     * @return The same builder instance.
     */
    public EmailBuilder flag(String flag) {
        this.flags.add(EmailFlag.of(flag));
        return this;
    }

    /**
     * Add a flag to email.
     *
     * @param flag
     * @return The same builder instance.
     */
    public EmailBuilder flag(EmailFlag flag) {
        this.flags.add(flag);
        return this;
    }

    /**
     * Build the {@link Email} message.
     *
     * @return An {@link ImmutableEmail}.
     */
    public Email build() {
        return new ImmutableEmail(
                new ImmutableEnvelope(from, replyTo, to, cc, bcc, subject),
                new ImmutableContent(
                        new TextPart(textContent),
                        new HtmlPart(htmlContent),
                        htmlImages, attachments
                )
        );
    }

}
