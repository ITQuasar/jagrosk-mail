package com.itquasar.multiverse.mail.util;

import com.itquasar.multiverse.mail.api.Contact;
import com.itquasar.multiverse.mail.part.Attachment;
import com.itquasar.multiverse.mail.part.Disposition;
import com.itquasar.multiverse.mail.part.GenericPart;
import com.itquasar.multiverse.mail.part.Inline;
import com.itquasar.multiverse.mail.part.Part;
import com.itquasar.multiverse.mail.server.listener.LoggerConnectionListener;
import com.itquasar.multiverse.mail.server.listener.LoggerFolderListenner;
import com.itquasar.multiverse.mail.server.listener.LoggerMessageChangedListener;
import com.itquasar.multiverse.mail.server.listener.LoggerMessageCountListener;
import com.itquasar.multiverse.mail.server.listener.LoggerStoreListenner;
import com.itquasar.multiverse.mail.server.listener.LoggerTransportListener;
import java.util.Collections;
import java.util.List;
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

    Part<Object> EMPTY_PART = new GenericPart<>(EMPTY_STRING, Disposition.NONE, EMPTY_STRING, null);
    Part<String> EMPTY_TEXT_PART = new GenericPart<>("text/plain", Disposition.NONE, EMPTY_STRING, null);
    Part<String> EMPTY_HTML_PART = new GenericPart<>("text/html", Disposition.NONE, EMPTY_STRING, null);

    List<Contact> NO_ONES = Collections.emptyList();
    Contact[] NO_ONES_ARRAY = new Contact[0];
    InternetAddress[] NO_ADDRESSES = new InternetAddress[0];

    List<Part<?>> NO_PARTS = Collections.emptyList();
    List<Inline<?>> NO_INLINES = Collections.emptyList();
    List<Attachment<?>> NO_ATTACHMENTS = Collections.emptyList();
    Part[] NO_PARTS_ARRAY = new Part[0];

    interface LoggerMailListener {

        LoggerConnectionListener CONNECTION = new LoggerConnectionListener();

        LoggerTransportListener TRANSPORT = new LoggerTransportListener();

        LoggerFolderListenner FOLDER = new LoggerFolderListenner();
        LoggerStoreListenner STORE = new LoggerStoreListenner();

        LoggerMessageChangedListener MESSAGE_CHANGED = new LoggerMessageChangedListener();
        LoggerMessageCountListener MESSAGE_COUNT = new LoggerMessageCountListener();
    }

}
