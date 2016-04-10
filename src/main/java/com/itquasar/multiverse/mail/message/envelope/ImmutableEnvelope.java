package com.itquasar.multiverse.mail.message.envelope;

import com.itquasar.multiverse.mail.EmailContact;
import com.itquasar.multiverse.mail.Envelope;
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

    private final EmailContact sender;
    private final List<EmailContact> from;
    private final List<EmailContact> replyTo;

    private final List<EmailContact> to;
    private final List<EmailContact> cc;
    private final List<EmailContact> bcc;

    private final String subject;

    private final Date receivedOn;

    public ImmutableEnvelope(EmailContact from,
            List<EmailContact> to, List<EmailContact> cc, List<EmailContact> bcc,
            String subject, Date receivedOn) {
        this(from, ClientUtils.emailContactToList(from), ClientUtils.emailContactToList(from), to, cc, bcc, subject, receivedOn);
    }

    public ImmutableEnvelope(EmailContact sender, List<EmailContact> from, List<EmailContact> replyTo,
            List<EmailContact> to, List<EmailContact> cc, List<EmailContact> bcc,
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
        this(EmailContact.fromInternetAddress(sender),
                EmailContact.fromInternetAddresses(from),
                EmailContact.fromInternetAddresses(replyTo),
                EmailContact.fromInternetAddresses(to),
                EmailContact.fromInternetAddresses(cc),
                EmailContact.fromInternetAddresses(bcc),
                subject, receivedOn);
    }

    @Override
    public EmailContact getSender() {
        return sender;
    }

    @Override
    public List<EmailContact> getFrom() {
        return from;
    }

    @Override
    public List<EmailContact> getReplyTo() {
        return replyTo;
    }

    @Override
    public List<EmailContact> getTo() {
        return to;
    }

    @Override
    public List<EmailContact> getCc() {
        return cc;
    }

    @Override
    public List<EmailContact> getBcc() {
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
