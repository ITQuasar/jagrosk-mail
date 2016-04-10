package com.itquasar.multiverse.mail.api.builder;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class EmailBuilderFactory {

    // FIXME: thing and create an API apable to have TEXT, HTML, plugable template engine, etc
    // Maybe create an interface a la {@link java.util.function.Function<T, R>} to process template in any given way
    public static EmailBuilder newBuilder(String mimeType) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

}
