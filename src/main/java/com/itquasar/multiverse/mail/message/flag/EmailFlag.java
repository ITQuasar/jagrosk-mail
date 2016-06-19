/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message.flag;

import javax.mail.Flags;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface EmailFlag extends Comparable<EmailFlag> {

    static String BLACK_FLAG = "\u2691";
    static String WHITE_FLAG = "\u2690";

    static EmailFlag of(Flags.Flag flag) {
        for (EmailFlag emailFlag : SystemEmailFlag.values()) {
            if (emailFlag.getJavaMailFlag().equals(flag)) {
                return emailFlag;
            }
        }
        throw new Error("Unknow flag!");
    }

    static EmailFlag of(String flag) {
        for (EmailFlag emailFlag : SystemEmailFlag.values()) {
            if (emailFlag.getName().equals(flag)) {
                return emailFlag;
            }
        }
        return new UserEmailFlag(flag);
    }

    default boolean isSystemFlag() {
        return false;
    }

    String getName();

    default Flags.Flag getJavaMailFlag() {
        throw new Error("Only system flags can be returned as Flags.Flag!");
    }

    @Override
    default int compareTo(EmailFlag o) {
        return this.getName().compareTo(o.getName());
    }

}
