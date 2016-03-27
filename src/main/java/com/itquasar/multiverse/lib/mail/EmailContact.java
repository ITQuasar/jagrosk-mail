package com.itquasar.multiverse.lib.mail;

import com.itquasar.multiverse.lib.mail.exception.EmailException;
import com.itquasar.multiverse.lib.mail.util.Constants;
import static com.itquasar.multiverse.lib.mail.util.Constants.RFC822_ADDRESS_SEPARATOR;
import com.itquasar.multiverse.lib.mail.util.FunctionUtils;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class EmailContact implements Comparable<EmailContact> {

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
        addresses = FunctionUtils.defaultOnNull(addresses, Constants.NO_ADDRESSES);
        List<EmailContact> contacts = new LinkedList<>();
        for (InternetAddress address : addresses) {
            contacts.add(fromInternetAddress(address));
        }
        return contacts;
    }

    public static InternetAddress[] toInternetAddresses(EmailContact... emailContacts) {
        emailContacts = FunctionUtils.defaultOnNull(emailContacts, Constants.NO_ONES_ARRAY);
        return emailContacts.length > 0
                ? toInternetAddresses(Arrays.asList(emailContacts))
                : Constants.NO_ADDRESSES;
    }

    public static InternetAddress[] toInternetAddresses(List<EmailContact> emailContacts) {
        emailContacts = FunctionUtils.defaultOnNull(emailContacts, Collections.EMPTY_LIST);
        InternetAddress[] addresses = new InternetAddress[emailContacts.size()];
        for (int i = 0; i < addresses.length; i++) {
            addresses[i] = emailContacts.get(i).toInternetAddress();
        }
        return addresses;
    }

    private final String name;
    private final String email;

    public EmailContact(String email) {
        this(Constants.EMPTY_STRING, email);
    }

    public EmailContact(String name, String email) {
        this.name = FunctionUtils.emptyOnNull(name);
        this.email = FunctionUtils.emptyOnNull(email);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int compareTo(EmailContact other) {
        // compare emails
        int c = email.compareTo(other.getEmail());
        // compare names if not null
        if (c == 0) {
            if (name == null) {
                c = -1;
            } else if (other.getName() == null) {
                c = 1;
            } else {
                c = name.compareTo(other.getName());
            }
        }
        return c;
    }

    public String toRFC822() {
        return email.isEmpty()
                ? Constants.EMPTY_STRING
                : (name.isEmpty()
                        ? Constants.EMPTY_STRING
                        : "\"" + name + "\" ") + "<" + email + ">";
    }

    public InternetAddress toInternetAddress() {
        return toInternetAddress(null);
    }

    public InternetAddress toInternetAddress(String charset) {
        try {
            return new InternetAddress(email, name, charset);
        } catch (UnsupportedEncodingException ex) {
            throw new EmailException("Error converting EmailContac to IntenetAddress", ex);
        }
    }

    @Override
    public String toString() {
        return toRFC822();
    }

}
