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
public class SystemEmailFlag implements EmailFlag {

    public static final SystemEmailFlag ANSWERED = new SystemEmailFlag("ANSWERED", Flags.Flag.ANSWERED);
    public static final SystemEmailFlag DELETED = new SystemEmailFlag("DELETED", Flags.Flag.DELETED);
    public static final SystemEmailFlag DRAFT = new SystemEmailFlag("DRAFT", Flags.Flag.DRAFT);
    public static final SystemEmailFlag FLAGGED = new SystemEmailFlag("FLAGGED", Flags.Flag.FLAGGED);
    public static final SystemEmailFlag RECENT = new SystemEmailFlag("RECENT", Flags.Flag.RECENT);
    public static final SystemEmailFlag SEEN = new SystemEmailFlag("SEEN", Flags.Flag.SEEN);
    public static final SystemEmailFlag USER = new SystemEmailFlag("USER", Flags.Flag.USER);

    public static EmailFlag[] values() {
        return new EmailFlag[]{
            ANSWERED, DELETED, DRAFT, FLAGGED, RECENT, SEEN, USER
        };
    }

    final String name;
    final Flags.Flag javaMailFlag;

    private SystemEmailFlag(String name, Flags.Flag javaMailFlag) {
        this.name = name;
        this.javaMailFlag = javaMailFlag;
    }

    @Override
    public boolean isSystemFlag() {
        return true;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Flags.Flag getJavaMailFlag() {
        return javaMailFlag;
    }

    @Override
    public String toString() {
        return BLACK_FLAG + getName();
    }

}
