/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message;

import com.itquasar.multiverse.mail.message.flag.EmailFlag;
import com.itquasar.multiverse.mail.util.FunctionUtils;
import java.util.HashSet;
import java.util.Set;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface ParsedEmail extends Email {

    /**
     *
     * @return The size of wrapped message or -1 when no message wrapped.
     */
    default int getSize() {
        return FunctionUtils.defaultOnNullOrException(() -> unwrap().getSize(), -1, LoggerFactory.getLogger(this.getClass()));
    }

    Message unwrap();

    /**
     *
     * @return All flags found on the parsed message (unwrapped), or empty
     * iterable.
     */
    @Override
    default public Iterable<EmailFlag> getFlags() {
        Set<EmailFlag> flags = new HashSet<>();
        Flags javaApiFlags;
        try {
            javaApiFlags = unwrap().getFlags();
        } catch (MessagingException ex) {
            LoggerFactory.getLogger(this.getClass()).error("Error getting Flags from java mail Message");
            javaApiFlags = new Flags();
        }
        for (Flags.Flag flag : javaApiFlags.getSystemFlags()) {
            flags.add(EmailFlag.of(flag));
        }
        for (String flag : javaApiFlags.getUserFlags()) {
            flags.add(EmailFlag.of(flag));
        }
        return flags;
    }

}
