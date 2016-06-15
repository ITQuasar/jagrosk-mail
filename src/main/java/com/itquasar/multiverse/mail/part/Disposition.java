package com.itquasar.multiverse.mail.part;

import com.itquasar.multiverse.mail.util.Constants;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public enum Disposition {
    INLINE, ATTACHMENT, NONE;

    private final String value;

    private Disposition() {
        this.value = this.name() != "NONE"
                ? this.name().toLowerCase()
                : Constants.EMPTY_STRING;
    }

    public static Disposition evaluate(String disposition) {
        if (disposition != null) {
            return Disposition.valueOf(disposition.toUpperCase());
        }
        return NONE;
    }

    public String value() {
        return value;
    }

}
