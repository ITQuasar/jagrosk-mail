package com.itquasar.multiverse.mail.message.envelope;

import com.itquasar.multiverse.mail.api.Envelope;
import com.itquasar.multiverse.mail.api.Contact;
import com.itquasar.multiverse.mail.util.ClientUtils;
import com.itquasar.multiverse.mail.util.FunctionUtils;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class ImmutableEnvelope implements Envelope {

    private final Contact sender;
    private final List<Contact> from;
    private final List<Contact> replyTo;

    private final List<Contact> to;
    private final List<Contact> cc;
    private final List<Contact> bcc;

    private final String subject;

    private final Date receivedOn;

    public ImmutableEnvelope(Contact from,
            List<Contact> to, List<Contact> cc, List<Contact> bcc,
            String subject, Date receivedOn) {
        this(from, ClientUtils.emailContactToList(from), ClientUtils.emailContactToList(from), to, cc, bcc, subject, receivedOn);
    }

    public ImmutableEnvelope(Contact sender, List<Contact> from, List<Contact> replyTo,
            List<Contact> to, List<Contact> cc, List<Contact> bcc,
            String subject, Date receivedOn) {
        this.sender = sender;
        this.from = Collections.unmodifiableList(FunctionUtils.emptyOnNull(from));
        this.replyTo = Collections.unmodifiableList(FunctionUtils.emptyOnNull(replyTo));
        this.to = Collections.unmodifiableList(FunctionUtils.emptyOnNull(to));
        this.cc = Collections.unmodifiableList(FunctionUtils.emptyOnNull(cc));
        this.bcc = Collections.unmodifiableList(FunctionUtils.emptyOnNull(bcc));
        this.subject = FunctionUtils.emptyOnNull(subject);
        // FIXME: null pointer
        this.receivedOn = receivedOn;
    }

    public ImmutableEnvelope(InternetAddress sender, InternetAddress[] from, InternetAddress[] replyTo,
            InternetAddress[] to, InternetAddress[] cc, InternetAddress[] bcc,
            String subject, Date receivedOn) {
        this(Contact.fromInternetAddress(sender),
                Contact.fromInternetAddresses(from),
                Contact.fromInternetAddresses(replyTo),
                Contact.fromInternetAddresses(to),
                Contact.fromInternetAddresses(cc),
                Contact.fromInternetAddresses(bcc),
                subject, receivedOn);
    }

    @Override
    public Contact getSender() {
        return sender;
    }

    @Override
    public List<Contact> getFrom() {
        return from;
    }

    @Override
    public List<Contact> getReplyTo() {
        return replyTo;
    }

    @Override
    public List<Contact> getTo() {
        return to;
    }

    @Override
    public List<Contact> getCc() {
        return cc;
    }

    @Override
    public List<Contact> getBcc() {
        return bcc;
    }

    @Override
    public String getSubject() {
        return this.subject;
    }

    // FIXME: null pointer
    @Override
    public Date getReceivedOn() {
        return receivedOn != null ? new Date(receivedOn.getTime()) : null;
    }

    @Override
    public String toString() {
        return "Envelope{" + "from=" + from + ", replyTo=" + replyTo + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc + '}';
    }

}
