/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message.flag;

import java.util.Set;
import java.util.TreeSet;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class EmailFlagsTest {

    @Test
    public void test() {
        EmailFlag del = SystemEmailFlags.DELETED;
        EmailFlag bah = UserEmailFlag.of("BAH");

        EmailFlags flags = new EmailFlags();
        flags.set(del);
        flags.set(bah);

        Set<EmailFlag> expectedFlagsSet = new TreeSet<>();
        expectedFlagsSet.add(del);
        expectedFlagsSet.add(bah);

        Set<EmailFlag> actualFlagsSet = new TreeSet();
        for (EmailFlag flag : flags) {
            actualFlagsSet.add(flag);
        }
        Assert.assertEquals(expectedFlagsSet, actualFlagsSet);
        Assert.assertEquals("EmailFlags{⚐BAH,⚑DELETED}", flags.toString());

        //remove
        flags.unset(bah);
        expectedFlagsSet.remove(bah);

        actualFlagsSet = new TreeSet();
        for (EmailFlag flag : flags) {
            actualFlagsSet.add(flag);
        }
        Assert.assertEquals(expectedFlagsSet, actualFlagsSet);
        Assert.assertEquals("EmailFlags{⚑DELETED}", flags.toString());

        // clear
        flags.clear();
        actualFlagsSet = new TreeSet();
        for (EmailFlag flag : flags) {
            actualFlagsSet.add(flag);
        }
        Assert.assertEquals(new TreeSet<>(), actualFlagsSet);
        Assert.assertEquals("EmailFlags{}", flags.toString());
    }
}
