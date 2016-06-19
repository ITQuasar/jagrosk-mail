package com.itquasar.multiverse.mail.message.envelope;

import com.itquasar.multiverse.mail.contact.Contact;
import com.itquasar.multiverse.mail.contact.ImmutableRecipients;
import com.itquasar.multiverse.mail.contact.ImmutableSenders;
import com.itquasar.multiverse.mail.contact.Recipients;
import com.itquasar.multiverse.mail.contact.Senders;
import com.itquasar.multiverse.mail.util.FunctionUtils;
import java.time.Instant;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class ImmutableEnvelope extends AbstractEnvelope {

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
            Set<Contact> to, Set<Contact> cc, Set<Contact> bcc,
            String subject) {
        this(FunctionUtils.toSet(from), to, cc, bcc, subject, null);
    }

    public ImmutableEnvelope(Contact from,
            Set<Contact> to, Set<Contact> cc, Set<Contact> bcc,
            String subject, Instant receivedOn) {
        this(FunctionUtils.toSet(from), to, cc, bcc, subject, receivedOn);
    }

    public ImmutableEnvelope(Set<Contact> from,
            Set<Contact> to, Set<Contact> cc, Set<Contact> bcc,
            String subject, Instant receivedOn) {
        this(NO_ONE, from, from, to, cc, bcc, subject, receivedOn);
    }

    public ImmutableEnvelope(Set<Contact> from, Set<Contact> replyTo,
            Set<Contact> to, Set<Contact> cc, Set<Contact> bcc,
            String subject) {
        this(NO_ONE, from, replyTo, to, cc, bcc, subject, null);
    }

    public ImmutableEnvelope(Contact sender, Set<Contact> from, Set<Contact> replyTo,
            Set<Contact> to, Set<Contact> cc, Set<Contact> bcc,
            String subject, Instant receivedOn) {
        this.senders = new ImmutableSenders(
                sender == null ? NO_ONE : sender,
                Collections.unmodifiableSet(FunctionUtils.emptyOnNull(from)),
                Collections.unmodifiableSet(FunctionUtils.emptyOnNull(replyTo))
        );
        this.recipients = new ImmutableRecipients(
                Collections.unmodifiableSet(FunctionUtils.emptyOnNull(to)),
                Collections.unmodifiableSet(FunctionUtils.emptyOnNull(cc)),
                Collections.unmodifiableSet(FunctionUtils.emptyOnNull(bcc))
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
