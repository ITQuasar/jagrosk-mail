/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.builder;

import com.itquasar.multiverse.mail.contact.Contact;
import com.itquasar.multiverse.mail.contact.ImmutableRecipients;
import com.itquasar.multiverse.mail.contact.Recipients;
import com.itquasar.multiverse.mail.util.Constants;
import com.itquasar.multiverse.mail.util.FunctionUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class RecipientsBuilder implements Constants {

    private List<Contact> to = new ArrayList<>();
    private List<Contact> cc = new ArrayList<>();
    private List<Contact> bcc = new ArrayList<>();

    public RecipientsBuilder() {
    }

    public RecipientsBuilder to(Contact... to) {
        this.to = FunctionUtils.toList(to);
        return this;
    }

    public RecipientsBuilder addTo(Contact... to) {
        this.to.addAll(FunctionUtils.toList(to));
        return this;
    }

    public RecipientsBuilder cc(Contact... cc) {
        this.cc = FunctionUtils.toList(cc);
        return this;
    }

    public RecipientsBuilder addCc(Contact... cc) {
        this.cc.addAll(FunctionUtils.toList(cc));
        return this;
    }

    public RecipientsBuilder bcc(Contact... bcc) {
        this.bcc = FunctionUtils.toList(bcc);
        return this;
    }

    public RecipientsBuilder addBcc(Contact... bcc) {
        this.bcc.addAll(FunctionUtils.toList(bcc));
        return this;
    }

    public Recipients build() {
        return new ImmutableRecipients(
                FunctionUtils.toSet(to),
                FunctionUtils.toSet(cc),
                FunctionUtils.toSet(bcc)
        );
    }

}
