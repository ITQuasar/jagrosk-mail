package com.itquasar.multiverse.mail.contact;

import com.itquasar.multiverse.mail.exception.EmailException;
import com.itquasar.multiverse.mail.util.Constants;
import static com.itquasar.multiverse.mail.util.Constants.RFC822_ADDRESS_SEPARATOR;
import com.itquasar.multiverse.mail.util.FunctionUtils;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class Contact implements Comparable<Contact> {

    public static String listToRFC822String(Contact... contacts) {
        return listToRFC822String(Arrays.asList(contacts));
    }

    public static String listToRFC822String(Collection<Contact> contacts) {
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

    public static Contact fromInternetAddress(InternetAddress address) {
        FunctionUtils.throwExceptionOnNullArgument(address, "internetAddress");
        return new Contact(address.getPersonal(), address.getAddress());
    }

    public static List<Contact> fromStrings(String... addresses) {
        addresses = FunctionUtils.defaultOnNull(addresses, Constants.EMPTY_STRING_ARRAY);
        return Stream.of(addresses).map((s) -> new Contact(s)).collect(Collectors.toList());
    }

    public static List<Contact> fromInternetAddresses(InternetAddress... addresses) {
        addresses = FunctionUtils.defaultOnNull(addresses, Constants.NO_ADDRESSES);
        List<Contact> contacts = new LinkedList<>();
        for (InternetAddress address : addresses) {
            contacts.add(fromInternetAddress(address));
        }
        return contacts;
    }

    public static InternetAddress[] toInternetAddresses(Contact... emailContacts) {
        emailContacts = FunctionUtils.defaultOnNull(emailContacts, Constants.NO_ONES_ARRAY);
        return emailContacts.length > 0
                ? toInternetAddresses(Arrays.asList(emailContacts))
                : Constants.NO_ADDRESSES;
    }

    public static InternetAddress[] toInternetAddresses(List<Contact> emailContacts) {
        emailContacts = FunctionUtils.defaultOnNull(emailContacts, Collections.EMPTY_LIST);
        InternetAddress[] addresses = new InternetAddress[emailContacts.size()];
        for (int i = 0; i < addresses.length; i++) {
            addresses[i] = emailContacts.get(i).toInternetAddress();
        }
        return addresses;
    }

    private final String name;
    private final String email;

    public Contact(String email) {
        this(Constants.EMPTY_STRING, email);
    }

    public Contact(String name, String email) {
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
    public int compareTo(Contact other) {
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
