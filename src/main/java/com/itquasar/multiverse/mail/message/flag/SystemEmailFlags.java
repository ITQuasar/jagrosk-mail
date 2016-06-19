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
public class SystemEmailFlags implements EmailFlag {

    public static final SystemEmailFlags ANSWERED = new SystemEmailFlags("ANSWERED", Flags.Flag.ANSWERED);
    public static final SystemEmailFlags DELETED = new SystemEmailFlags("DELETED", Flags.Flag.DELETED);
    public static final SystemEmailFlags DRAFT = new SystemEmailFlags("DRAFT", Flags.Flag.DRAFT);
    public static final SystemEmailFlags FLAGGED = new SystemEmailFlags("FLAGGED", Flags.Flag.FLAGGED);
    public static final SystemEmailFlags RECENT = new SystemEmailFlags("RECENT", Flags.Flag.RECENT);
    public static final SystemEmailFlags SEEN = new SystemEmailFlags("SEEN", Flags.Flag.SEEN);
    public static final SystemEmailFlags USER = new SystemEmailFlags("USER", Flags.Flag.USER);

    public static SystemEmailFlags[] values() {
        return new SystemEmailFlags[]{
            ANSWERED, DELETED, DRAFT, FLAGGED, RECENT, SEEN, USER
        };
    }

    final String name;
    final Flags.Flag javaMailFlag;

    private SystemEmailFlags(String name, Flags.Flag javaMailFlag) {
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
