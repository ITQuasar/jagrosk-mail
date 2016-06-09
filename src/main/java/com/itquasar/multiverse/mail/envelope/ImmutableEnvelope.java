package com.itquasar.multiverse.mail.envelope;

import com.itquasar.multiverse.mail.contact.Contact;
import com.itquasar.multiverse.mail.contact.ImmutableRecipients;
import com.itquasar.multiverse.mail.contact.ImmutableSenders;
import com.itquasar.multiverse.mail.contact.Recipients;
import com.itquasar.multiverse.mail.contact.Senders;
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

    private final Senders senders;
    private final Recipients recipients;

    private final String subject;

    private final Optional<Instant> receivedOn;

    public ImmutableEnvelope(Senders senders, Recipients recipients, String subject) {
        this(senders, recipients, subject, null);
    }

    public ImmutableEnvelope(Senders senders, Recipients recipients, String subject,
            Optional<Instant> receivedOn) {
        this.senders = senders == null ? NO_SENDERS : senders;
        this.recipients = recipients == null ? NO_RECIPIENTS : recipients;
        this.subject = subject;
        this.receivedOn = receivedOn;
    }

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
        this.senders = new ImmutableSenders(
                sender == null ? NO_ONE : sender,
                Collections.unmodifiableList(FunctionUtils.emptyOnNull(from)),
                Collections.unmodifiableList(FunctionUtils.emptyOnNull(replyTo))
        );
        this.recipients = new ImmutableRecipients(
                Collections.unmodifiableList(FunctionUtils.emptyOnNull(to)),
                Collections.unmodifiableList(FunctionUtils.emptyOnNull(cc)),
                Collections.unmodifiableList(FunctionUtils.emptyOnNull(bcc))
        );
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
    public Senders getSenders() {
        return senders;
    }

    @Override
    public Recipients getRecipients() {
        return recipients;
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
        return "ImmutableEnvelope{" + "senders=" + senders + ", recipients=" + recipients + ", subject=" + subject + ", receivedOn=" + receivedOn + '}';
    }

}
