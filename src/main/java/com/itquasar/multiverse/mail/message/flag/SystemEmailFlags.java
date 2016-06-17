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
public enum SystemEmailFlags implements EmailFlag {

    ANSWERED(Flags.Flag.ANSWERED),
    DELETED(Flags.Flag.DELETED),
    DRAFT(Flags.Flag.DRAFT),
    FLAGGED(Flags.Flag.FLAGGED),
    RECENT(Flags.Flag.RECENT),
    SEEN(Flags.Flag.SEEN),
    USER(Flags.Flag.USER);

    final Flags.Flag javaMailFlag;

    private SystemEmailFlags(Flags.Flag javaMailFlag) {
        this.javaMailFlag = javaMailFlag;
    }

    @Override
    public boolean isSystemFlag() {
        return true;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public Flags.Flag getJavaMailFlag() {
        return javaMailFlag;
    }

}
