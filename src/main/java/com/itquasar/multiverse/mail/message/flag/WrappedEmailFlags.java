/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message.flag;

import com.itquasar.multiverse.mail.util.FunctionUtils;
import javax.mail.Flags;
import javax.mail.Message;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class WrappedEmailFlags extends EmailFlags {

    private final Message message;

    public WrappedEmailFlags(Message message) {
        super();
        this.message = message;
    }

    @Override
    public EmailFlags clear() {
        return FunctionUtils.tryOrThrow((x) -> {
            message.setFlags(message.getFlags(), false);
            return this;
        }, "Error clearing message flags");
    }

    @Override
    public EmailFlags unset(EmailFlag flag) {
        return FunctionUtils.tryOrThrow(
                (f) -> {
                    Flags flags = message.getFlags();
                    if (f.isSystemFlag()) {
                        flags.remove(f.getJavaMailFlag());
                    } else {
                        flags.remove(f.getName());
                    }
                    this.clear();
                    message.setFlags(flags, true);
                    return this;
                },
                flag,
                "Error removing flag from message"
        );
    }

    @Override
    public EmailFlags set(EmailFlag flag) {
        return FunctionUtils.tryOrThrow(
                (f) -> {
                    Flags flags = message.getFlags();
                    if (f.isSystemFlag()) {
                        flags.add(f.getJavaMailFlag());
                    } else {
                        flags.add(f.getName());
                    }
                    this.clear();
                    message.setFlags(flags, true);
                    return this;
                },
                flag,
                "Error setting flag on message"
        );

    }

}
