package com.itquasar.multiverse.lib.mail;

import static com.itquasar.multiverse.lib.mail.util.Constants.EMPTY_STRING;
import static com.itquasar.multiverse.lib.mail.util.Constants.RFC822_ADDRESS_SEPARATOR;
import com.itquasar.multiverse.lib.mail.util.FunctionUtils;
import static com.itquasar.multiverse.lib.mail.util.FunctionUtils.emptyOnNull;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class EmailContact {

    public static String listToRFC822String(EmailContact... contacts) {
        return listToRFC822String(Arrays.asList(contacts));
    }

    public static String listToRFC822String(Collection<EmailContact> contacts) {
        StringBuilder builder = new StringBuilder();
        contacts.stream()
                .map((contact) -> contact.toRFC822())
                .filter((rfc822Str) -> !rfc822Str.isEmpty())
                .forEach(
                        (rfc822Str)
                        -> builder.append(rfc822Str).append(RFC822_ADDRESS_SEPARATOR)
                );
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    public static EmailContact fromInternetAddress(InternetAddress address) {
        FunctionUtils.checkNullArgument(address, "internetAddress");
        return new EmailContact(address.getPersonal(), address.getAddress());
    }

    public static List<EmailContact> fromInternetAddresses(InternetAddress... addresses) {
        List<EmailContact> contacts = new LinkedList<>();
        if (addresses != null) {
            for (InternetAddress address : addresses) {
                contacts.add(fromInternetAddress(address));
            }
        }
        return contacts;
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
                : (name.isEmpty()
                        ? EMPTY_STRING
                        : "\"" + name + "\" ") + "<" + email + ">";
    }

    @Override
    public String toString() {
        return toRFC822();
    }

}
