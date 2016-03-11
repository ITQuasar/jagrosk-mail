package com.itquasar.multiverse.lib.mail;

import java.util.Collections;
import java.util.List;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class Envelope {

    private final List<EmailContact> from;
    private final List<EmailContact> replyTo;

    private final List<EmailContact> to;
    private final List<EmailContact> cc;
    private final List<EmailContact> bcc;

    public Envelope(List<EmailContact> from, List<EmailContact> replyTo,
            List<EmailContact> to, List<EmailContact> cc, List<EmailContact> bcc) {
        this.from = Collections.unmodifiableList(from);
        this.replyTo = Collections.unmodifiableList(replyTo);
        this.to = Collections.unmodifiableList(to);
        this.cc = Collections.unmodifiableList(cc);
        this.bcc = Collections.unmodifiableList(bcc);
    }

    public Envelope(InternetAddress[] from, InternetAddress[] replyTo,
            InternetAddress[] to, InternetAddress[] cc, InternetAddress[] bcc) {
        this(EmailContact.fromInternetAddresses(from),
                EmailContact.fromInternetAddresses(replyTo),
                EmailContact.fromInternetAddresses(to),
                EmailContact.fromInternetAddresses(cc),
                EmailContact.fromInternetAddresses(bcc));
    }

    public List<EmailContact> getFrom() {
        return from;
    }

    public List<EmailContact> getReplyTo() {
        return replyTo;
    }

    public List<EmailContact> getTo() {
        return to;
    }

    public List<EmailContact> getCc() {
        return cc;
    }

    public List<EmailContact> getBcc() {
        return bcc;
    }

    @Override
    public String toString() {
        return "Envelope{" + "from=" + from + ", replyTo=" + replyTo + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc + '}';
    }

}
