/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.lib.mail;

import com.itquasar.multiverse.lib.mail.util.Constants;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class EmailContactTest {

    private static final EmailContact[] contacts = new EmailContact[]{
        Constants.NO_ONE,
        new EmailContact("a@b.c"),
        new EmailContact("A B C", "a@b.c"),
        new EmailContact(null),
        new EmailContact(null, null)
    };

    private static final String ALL_CONTACTS = "<a@b.c>,A B C<a@b.c>";

    /**
     * Test of toRFC822 method, of class EmailContact.
     *
     * @throws javax.mail.internet.AddressException
     */
    @Test
    public void testToRFC822() throws AddressException {
        System.out.println("toRFC822 - test if always works parse with InternetAddress");

        for (EmailContact contact : contacts) {
            Assert.assertNotNull(
                    InternetAddress.parse(contact.toRFC822())
            );
        }
    }

    @Test
    public void testListToRFC822String() {
        System.out.println("listToRFC822String");
        String result = EmailContact.listToRFC822String(contacts);
        Assert.assertEquals(ALL_CONTACTS, result);
    }

}
