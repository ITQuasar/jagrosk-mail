package com.itquasar.multiverse.lib.mail.envelope;

import com.itquasar.multiverse.lib.mail.EmailContact;
import com.itquasar.multiverse.lib.mail.Envelope;
import com.itquasar.multiverse.lib.mail.util.Utils;
import java.util.Collections;
import java.util.List;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class ImmutableEnvelope implements Envelope {

    private final List<EmailContact> from;
    private final List<EmailContact> replyTo;

    private final List<EmailContact> to;
    private final List<EmailContact> cc;
    private final List<EmailContact> bcc;

    private final String subject;

    public ImmutableEnvelope(EmailContact from,
            List<EmailContact> to, List<EmailContact> cc, List<EmailContact> bcc,
            String subject) {
        this(Utils.emailContactToList(from), Utils.emailContactToList(from), to, cc, bcc, subject);
    }

    public ImmutableEnvelope(List<EmailContact> from, List<EmailContact> replyTo,
            List<EmailContact> to, List<EmailContact> cc, List<EmailContact> bcc,
            String subject) {
        this.from = Collections.unmodifiableList(from);
        this.replyTo = Collections.unmodifiableList(replyTo);
        this.to = Collections.unmodifiableList(to);
        this.cc = Collections.unmodifiableList(cc);
        this.bcc = Collections.unmodifiableList(bcc);
        this.subject = Utils.emptyOnNull(subject);
    }

    public ImmutableEnvelope(InternetAddress[] from, InternetAddress[] replyTo,
            InternetAddress[] to, InternetAddress[] cc, InternetAddress[] bcc,
            String subject) {
        this(EmailContact.fromInternetAddresses(from),
                EmailContact.fromInternetAddresses(replyTo),
                EmailContact.fromInternetAddresses(to),
                EmailContact.fromInternetAddresses(cc),
                EmailContact.fromInternetAddresses(bcc),
                subject);
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

    @Override
    public String toString() {
        return "Envelope{" + "from=" + from + ", replyTo=" + replyTo + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc + '}';
    }

}
