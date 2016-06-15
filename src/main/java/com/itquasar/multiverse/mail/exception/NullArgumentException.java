package com.itquasar.multiverse.mail.exception;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class NullArgumentException extends NullPointerException {

    public NullArgumentException(String argumentName) {
        super("Null value received on argument [" + argumentName + "]");
    }

}
