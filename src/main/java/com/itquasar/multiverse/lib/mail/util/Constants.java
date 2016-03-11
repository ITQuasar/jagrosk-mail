package com.itquasar.multiverse.lib.mail.util;

import com.itquasar.multiverse.lib.mail.EmailContact;
import com.itquasar.multiverse.lib.mail.part.GenericPart;
import com.itquasar.multiverse.lib.mail.part.Part;
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
    Part<String> EMPTY_TEXT_PART = new GenericPart<>("text/plain", EMPTY_STRING);
    Part<String> EMPTY_HTML_PART = new GenericPart<>("text/html", EMPTY_STRING);

    List<EmailContact> NO_ONES = Collections.emptyList();
    List<Part> NO_PARTS = Collections.emptyList();

}
