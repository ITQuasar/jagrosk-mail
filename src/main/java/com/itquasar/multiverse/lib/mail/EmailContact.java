package com.itquasar.multiverse.lib.mail;

import static com.itquasar.multiverse.lib.mail.util.Constants.EMPTY_STRING;
import static com.itquasar.multiverse.lib.mail.util.Constants.RFC822_ADDRESS_SEPARATOR;
import static com.itquasar.multiverse.lib.mail.util.Utils.emptyOnNull;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class EmailContact {

    public static String toRFC822String(List<EmailContact> contacts) {
        StringBuilder builder = new StringBuilder();
        contacts.stream()
                .forEach(
                        (contact)
                        -> builder.append(contact.toRFC822()).append(RFC822_ADDRESS_SEPARATOR)
                );
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    private final String name;
    private final String email;

    public EmailContact(String email) {
        this(EMPTY_STRING, email);
    }

    public EmailContact(String name, String email) {
        this.name = emptyOnNull(name);
        this.email = emptyOnNull(email);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String toRFC822() {
        return email.isEmpty()
                ? EMPTY_STRING
                : name + "<" + email + ">";
    }

    @Override
    public String toString() {
        return toRFC822();
    }

}
