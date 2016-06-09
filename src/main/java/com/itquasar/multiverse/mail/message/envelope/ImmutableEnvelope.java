package com.itquasar.multiverse.mail.message.envelope;

import com.itquasar.multiverse.mail.api.Contact;
import com.itquasar.multiverse.mail.api.Envelope;
import com.itquasar.multiverse.mail.util.FunctionUtils;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

    private final Optional<Instant> receivedOn;

    public ImmutableEnvelope(Contact from,
            List<Contact> to, List<Contact> cc, List<Contact> bcc,
            String subject) {
        this(FunctionUtils.toList(from), to, cc, bcc, subject, null);
    }

    public ImmutableEnvelope(Contact from,
            List<Contact> to, List<Contact> cc, List<Contact> bcc,
            String subject, Instant receivedOn) {
        this(FunctionUtils.toList(from), to, cc, bcc, subject, receivedOn);
    }

    public ImmutableEnvelope(List<Contact> from,
            List<Contact> to, List<Contact> cc, List<Contact> bcc,
            String subject, Instant receivedOn) {
        this(from.get(0), from, from, to, cc, bcc, subject, receivedOn);
    }

    public ImmutableEnvelope(List<Contact> from, List<Contact> replyTo,
            List<Contact> to, List<Contact> cc, List<Contact> bcc,
            String subject) {
        this(from.get(0), from, replyTo, to, cc, bcc, subject, null);
    }

    public ImmutableEnvelope(Contact sender, List<Contact> from, List<Contact> replyTo,
            List<Contact> to, List<Contact> cc, List<Contact> bcc,
            String subject, Instant receivedOn) {
        this.sender = sender;
        this.from = Collections.unmodifiableList(FunctionUtils.emptyOnNull(from));
        this.replyTo = Collections.unmodifiableList(FunctionUtils.emptyOnNull(replyTo));
        this.to = Collections.unmodifiableList(FunctionUtils.emptyOnNull(to));
        this.cc = Collections.unmodifiableList(FunctionUtils.emptyOnNull(cc));
        this.bcc = Collections.unmodifiableList(FunctionUtils.emptyOnNull(bcc));
        this.subject = FunctionUtils.emptyOnNull(subject);
        this.receivedOn = Optional.ofNullable(receivedOn);
    }

    public ImmutableEnvelope(InternetAddress sender, InternetAddress[] from, InternetAddress[] replyTo,
            InternetAddress[] to, InternetAddress[] cc, InternetAddress[] bcc,
            String subject, Instant receivedOn) {
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

    @Override
    public Optional<Instant> getReceivedOn() {
        return receivedOn;
    }

    @Override
    public String toString() {
        return "ImmutableEnvelope{" + "sender=" + sender + ", from=" + from + ", replyTo=" + replyTo + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc + ", subject=" + subject + ", receivedOn=" + receivedOn + '}';
    }

}
