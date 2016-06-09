package com.itquasar.multiverse.mail;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface Protocol {

    enum Type {
        /**
         * For receive e-mails.
         */
        STORE,
        /**
         * For send e-mails.
         */
        TRANSPORT;
    }

    String javamailName();

    Type getType();

}
