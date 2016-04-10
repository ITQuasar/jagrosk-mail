package com.itquasar.multiverse.mail.server;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 * @param <T> Type of object to intercept.
 */
@FunctionalInterface
public interface Interceptor<T> {

    boolean allow(T t);

}
