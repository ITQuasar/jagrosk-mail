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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class SendersBuilder implements Constants {

    private Contact sender = NO_ONE;
    private List<Contact> from = new ArrayList<>();
    private List<Contact> replyTo = new ArrayList<>();

    public SendersBuilder() {
    }

    public SendersBuilder sender(Contact contact) {
        this.sender = sender;
        return this;
    }

    public SendersBuilder from(Contact... from) {
        this.from = FunctionUtils.toList(from);
        return this;
    }

    public SendersBuilder addFrom(Contact... from) {
        this.from.addAll(FunctionUtils.toList(from));
        return this;
    }

    public SendersBuilder replyTo(Contact... replyTo) {
        this.replyTo = FunctionUtils.toList(replyTo);
        return this;
    }

    public SendersBuilder addReplyTo(Contact... replyTo) {
        this.replyTo.addAll(FunctionUtils.toList(replyTo));
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
