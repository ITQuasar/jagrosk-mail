/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message.flag;

import com.itquasar.multiverse.mail.util.FunctionUtils;
import java.util.Objects;
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
        this.message = FunctionUtils.tryOrThrow(
                (msg) -> {
                    for (Flags.Flag flag : msg.getFlags().getSystemFlags()) {
                        super.set(EmailFlag.of(flag));
                    }
                    for (String flag : msg.getFlags().getUserFlags()) {
                        super.set(EmailFlag.of(flag));
                    }
                    return message;
                },
                message,
                "Error reading message flags"
        );
    }

    private EmailFlags clearMessageFlags() {
        return FunctionUtils.tryOrThrow((x) -> {
            message.setFlags(message.getFlags(), false);
            return this;
        }, "Error clearing message flags");
    }

    @Override
    public EmailFlags clear() {
        super.clear();
        this.clearMessageFlags();
        return this;
    }

    @Override
    public EmailFlags unset(EmailFlag flag) {
        super.unset(flag);
        return FunctionUtils.tryOrThrow(
                (f) -> {
                    Flags flags = message.getFlags();
                    if (f.isSystemFlag()) {
                        flags.remove(f.getJavaMailFlag());
                    } else {
                        flags.remove(f.getName());
                    }
                    this.clearMessageFlags();
                    message.setFlags(flags, true);
                    return this;
                },
                flag,
                "Error removing flag from message"
        );
    }

    @Override
    public EmailFlags set(EmailFlag flag) {
        super.set(flag);
        return FunctionUtils.tryOrThrow(
                (f) -> {
                    Flags flags = message.getFlags();
                    if (f.isSystemFlag()) {
                        flags.add(f.getJavaMailFlag());
                    } else {
                        flags.add(f.getName());
                    }
                    this.clearMessageFlags();
                    message.setFlags(flags, true);
                    return this;
                },
                flag,
                "Error setting flag on message"
        );
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.message);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WrappedEmailFlags other = (WrappedEmailFlags) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Wrapped" + super.toString();
    }

}
