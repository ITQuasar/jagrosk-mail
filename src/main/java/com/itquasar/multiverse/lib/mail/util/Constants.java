package com.itquasar.multiverse.lib.mail.util;

import com.itquasar.multiverse.lib.mail.EmailContact;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface Constants {

    String EMPTY_STRING = "";
    String RFC822_ADDRESS_SEPARATOR = ",";
    EmailContact NO_ONE = new EmailContact(EMPTY_STRING, EMPTY_STRING);

}
