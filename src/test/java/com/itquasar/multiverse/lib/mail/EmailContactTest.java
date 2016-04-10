/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.lib.mail;

import com.itquasar.multiverse.mail.api.EmailContact;
import com.itquasar.multiverse.mail.util.Constants;
import java.util.Map;
import java.util.TreeMap;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class EmailContactTest {

    private static final Map<EmailContact, String> contacts = new TreeMap<EmailContact, String>() {
        {
            put(Constants.NO_ONE, "");
            put(new EmailContact("a&@b.c"), "<a&@b.c>");
            put(new EmailContact("A B C", "a@b.c"), "\"A B C\" <a@b.c>");
            put(new EmailContact(null), "");
            put(new EmailContact(null, null), "");
        }
    };

    private static final String ALL_CONTACTS = "<a&@b.c>,\"A B C\" <a@b.c>";

    /**
     * Test of toRFC822 method, of class EmailContact.
     *
     * @throws javax.mail.internet.AddressException
     */
    @Test
    public void testToRFC822() throws AddressException {
        System.out.println("toRFC822 - test if always works parse with InternetAddress");

        for (EmailContact contact : contacts.keySet()) {
            InternetAddress[] addresses = InternetAddress.parse(contact.toRFC822());
            Assert.assertNotNull(addresses);
            for (InternetAddress address : addresses) {
                System.out.println(address);
            }
            Assert.assertEquals(contacts.get(contact), contact.toRFC822());
        }
    }

    @Test
    public void testListToRFC822String() throws AddressException {
        System.out.println("listToRFC822String");
        String result = EmailContact.listToRFC822String(contacts.keySet());
        InternetAddress[] addresses = InternetAddress.parse(result);
        Assert.assertNotNull(addresses);
        for (InternetAddress address : addresses) {
            System.out.println(address);
        }
        Assert.assertEquals(ALL_CONTACTS, result);
    }

}
