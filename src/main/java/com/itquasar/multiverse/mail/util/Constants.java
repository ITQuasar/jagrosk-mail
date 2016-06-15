package com.itquasar.multiverse.mail.util;

import com.itquasar.multiverse.mail.contact.Contact;
import com.itquasar.multiverse.mail.contact.ImmutableRecipients;
import com.itquasar.multiverse.mail.contact.ImmutableSenders;
import com.itquasar.multiverse.mail.contact.Recipients;
import com.itquasar.multiverse.mail.contact.Senders;
import com.itquasar.multiverse.mail.part.Attachment;
import com.itquasar.multiverse.mail.part.Disposition;
import com.itquasar.multiverse.mail.part.GenericPart;
import com.itquasar.multiverse.mail.part.HtmlPart;
import com.itquasar.multiverse.mail.part.Inline;
import com.itquasar.multiverse.mail.part.Part;
import com.itquasar.multiverse.mail.part.TextPart;
import com.itquasar.multiverse.mail.server.listener.LoggerConnectionListener;
import com.itquasar.multiverse.mail.server.listener.LoggerFolderListenner;
import com.itquasar.multiverse.mail.server.listener.LoggerMessageChangedListener;
import com.itquasar.multiverse.mail.server.listener.LoggerMessageCountListener;
import com.itquasar.multiverse.mail.server.listener.LoggerStoreListenner;
import com.itquasar.multiverse.mail.server.listener.LoggerTransportListener;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface Constants {

    String EMPTY_STRING = "";
    String[] EMPTY_STRING_ARRAY = new String[0];
    String RFC822_ADDRESS_SEPARATOR = ",";

    Contact NO_ONE = new Contact(EMPTY_STRING, EMPTY_STRING);
    List<Contact> NO_ONES_LIST = Collections.emptyList();
    Set<Contact> NO_ONES_SET = Collections.emptySet();
    Contact[] NO_ONES_ARRAY = new Contact[0];
    InternetAddress[] NO_ADDRESSES = new InternetAddress[0];

    Senders NO_SENDERS = new ImmutableSenders(NO_ONES_SET);
    Recipients NO_RECIPIENTS = new ImmutableRecipients(NO_ONES_SET);

    Part<Object> EMPTY_PART = new GenericPart<>(EMPTY_STRING, Disposition.NONE, EMPTY_STRING, null);
    TextPart EMPTY_TEXT_PART = new TextPart(EMPTY_STRING, Disposition.NONE, null);
    HtmlPart EMPTY_HTML_PART = new HtmlPart(EMPTY_STRING, Disposition.NONE, null);

    List<Part<?>> NO_PARTS = Collections.emptyList();
    List<Inline<?>> NO_INLINES = Collections.emptyList();
    List<Attachment<?>> NO_ATTACHMENTS = Collections.emptyList();
    Part[] NO_PARTS_ARRAY = new Part[0];

    InternetAddress[] NO_INTERNET_ADDRESS_ARRAY = new InternetAddress[0];

    interface LoggerMailListener {

        LoggerConnectionListener CONNECTION = new LoggerConnectionListener();

        LoggerTransportListener TRANSPORT = new LoggerTransportListener();

        LoggerFolderListenner FOLDER = new LoggerFolderListenner();
        LoggerStoreListenner STORE = new LoggerStoreListenner();

        LoggerMessageChangedListener MESSAGE_CHANGED = new LoggerMessageChangedListener();
        LoggerMessageCountListener MESSAGE_COUNT = new LoggerMessageCountListener();
    }

}
