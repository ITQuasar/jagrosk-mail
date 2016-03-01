package com.itquasar.multiverse.lib.mail;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface Part<T> {

    enum Disposition {

        INLINE, ATTACHMENT, FLOWED, NONE;

        public static Disposition evaluate(String disposition) {
            if (disposition != null) {
                return Disposition.valueOf(disposition.toUpperCase());
            }
            return FLOWED;
        }

        public String value() {
            return this.name().toLowerCase();
        }
    }

    String getContentId();

    String getMimeType();

    String getName();

    T getContent();

    Disposition getDisposition();

}
