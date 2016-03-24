package com.itquasar.multiverse.lib.mail.util;

import com.itquasar.multiverse.lib.mail.EmailContact;
import com.itquasar.multiverse.lib.mail.part.GenericPart;
import com.itquasar.multiverse.lib.mail.part.Part;
import com.itquasar.multiverse.lib.mail.server.listener.LoggerConnectionListener;
import com.itquasar.multiverse.lib.mail.server.listener.LoggerFolderListenner;
import com.itquasar.multiverse.lib.mail.server.listener.LoggerMessageChangedListener;
import com.itquasar.multiverse.lib.mail.server.listener.LoggerMessageCountListener;
import com.itquasar.multiverse.lib.mail.server.listener.LoggerStoreListenner;
import com.itquasar.multiverse.lib.mail.server.listener.LoggerTransportListener;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface Constants {

    String EMPTY_STRING = "";
    String RFC822_ADDRESS_SEPARATOR = ",";

    EmailContact NO_ONE = new EmailContact(EMPTY_STRING, EMPTY_STRING);

    Part<Object> EMPTY_PART = new GenericPart<>(EMPTY_STRING, Part.Disposition.NONE, EMPTY_STRING, null);
    Part<String> EMPTY_TEXT_PART = new GenericPart<>("text/plain", Part.Disposition.NONE, EMPTY_STRING, null);
    Part<String> EMPTY_HTML_PART = new GenericPart<>("text/html", Part.Disposition.NONE, EMPTY_STRING, null);

    List<EmailContact> NO_ONES = Collections.emptyList();
    List<Part> NO_PARTS = Collections.emptyList();

    interface LoggerMailListener {

        LoggerConnectionListener CONNECTION = new LoggerConnectionListener();

        LoggerTransportListener TRANSPORT = new LoggerTransportListener();

        LoggerFolderListenner FOLDER = new LoggerFolderListenner();
        LoggerStoreListenner STORE = new LoggerStoreListenner();

        LoggerMessageChangedListener MESSAGE_CHANGED = new LoggerMessageChangedListener();
        LoggerMessageCountListener MESSAGE_COUNT = new LoggerMessageCountListener();
    }

}
