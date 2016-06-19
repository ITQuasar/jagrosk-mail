/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message.flag;

import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class WrappedEmailFlagsTest {

    @Test
    public void test() throws MessagingException {
        Message message = new MimeMessage(Session.getDefaultInstance(new Properties()));
        message.setFlag(Flags.Flag.ANSWERED, true);

        EmailFlag ans = SystemEmailFlag.ANSWERED;
        EmailFlag del = SystemEmailFlag.DELETED;
        EmailFlag bah = UserEmailFlag.of("BAH");

        EmailFlags flags = new WrappedEmailFlags(message);
        flags.set(del);
        flags.set(bah);

        Set<EmailFlag> expectedFlagsSet = new TreeSet<>();
        expectedFlagsSet.add(ans);
        expectedFlagsSet.add(del);
        expectedFlagsSet.add(bah);

        Set<EmailFlag> actualFlagsSet = new TreeSet();
        for (EmailFlag flag : flags) {
            actualFlagsSet.add(flag);
        }
        Assert.assertEquals(expectedFlagsSet, actualFlagsSet);
        Assert.assertEquals("WrappedEmailFlags{⚑ANSWERED,⚐BAH,⚑DELETED}", flags.toString());

        //remove
        flags.unset(bah);
        expectedFlagsSet.remove(bah);

        actualFlagsSet = new TreeSet();
        for (EmailFlag flag : flags) {
            actualFlagsSet.add(flag);
        }
        Assert.assertEquals(expectedFlagsSet, actualFlagsSet);
        Assert.assertEquals("WrappedEmailFlags{⚑ANSWERED,⚑DELETED}", flags.toString());

        // clear
        flags.clear();
        actualFlagsSet = new TreeSet();
        for (EmailFlag flag : flags) {
            actualFlagsSet.add(flag);
        }
        Assert.assertEquals(new TreeSet<>(), actualFlagsSet);
        Assert.assertEquals("WrappedEmailFlags{}", flags.toString());

        flags = new WrappedEmailFlags(message);
        actualFlagsSet = new TreeSet<>();
        for (EmailFlag flag : flags) {
            actualFlagsSet.add(flag);
        }
        Assert.assertEquals(new TreeSet<>(), actualFlagsSet);
        Assert.assertEquals("WrappedEmailFlags{}", flags.toString());
    }
}
