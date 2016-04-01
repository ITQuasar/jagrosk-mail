package com.itquasar.multiverse.lib.mail.server.listener;

import javax.mail.event.StoreEvent;
import javax.mail.event.StoreListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class LoggerStoreListenner implements StoreListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerStoreListenner.class);

    @Override
    public void notification(StoreEvent e) {
        LOGGER.trace("Store notification event [{}]", e);
    }

}
