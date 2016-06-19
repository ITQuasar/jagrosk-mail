/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.builder;

import com.itquasar.multiverse.mail.contact.Contact;
import com.itquasar.multiverse.mail.contact.ImmutableSenders;
import com.itquasar.multiverse.mail.contact.Senders;
import com.itquasar.multiverse.mail.util.Constants;
import com.itquasar.multiverse.mail.util.FunctionUtils;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class SendersBuilder implements Constants {

    private Contact sender = NO_ONE;
    private Set<Contact> from = new HashSet<>();
    private Set<Contact> replyTo = new HashSet<>();

    public SendersBuilder() {
    }

    public SendersBuilder sender(String senderEmail) {
        return sender(new Contact(senderEmail));
    }

    public SendersBuilder sender(Contact senderContact) {
        this.sender = sender;
        return this;
    }

    public SendersBuilder from(String... fromEmails) {
        this.from = Contact.fromStrings(fromEmails);
        return this;
    }

    public SendersBuilder from(Contact... fromContacts) {
        this.from = FunctionUtils.toSet(fromContacts);
        return this;
    }

    public SendersBuilder replyTo(String... replyToEmails) {
        this.replyTo = Contact.fromStrings(replyToEmails);
        return this;
    }

    public SendersBuilder replyTo(Contact... replyToContacts) {
        this.replyTo = FunctionUtils.toSet(replyToContacts);
        return this;
    }

    public Senders build() {
        return new ImmutableSenders(
                sender,
                FunctionUtils.toSet(from),
                FunctionUtils.toSet(replyTo)
        );
    }
}
