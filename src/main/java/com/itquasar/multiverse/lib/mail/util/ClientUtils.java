package com.itquasar.multiverse.lib.mail.util;

import com.itquasar.multiverse.lib.mail.EmailContact;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public final class ClientUtils {

    private ClientUtils() {
    }

    public static List<EmailContact> emailContactToList(EmailContact... contacts) {
        List<EmailContact> list = new LinkedList<>();
        if (contacts != null) {
            list.addAll(Arrays.asList(contacts));
        }
        return list;
    }

}
