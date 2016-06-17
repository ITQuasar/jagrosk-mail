/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message;

import com.itquasar.multiverse.mail.util.FunctionUtils;
import javax.mail.Message;
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
}
