package com.itquasar.multiverse.mail.server.listener;

import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class LoggerConnectionListener implements ConnectionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerConnectionListener.class);

    @Override
    public void opened(ConnectionEvent e) {
        LOGGER.trace("Connection opened event [{}]", e);
    }

    @Override
    public void disconnected(ConnectionEvent e) {
        LOGGER.trace("Connection disconnected event [{}]", e);
    }

    @Override
    public void closed(ConnectionEvent e) {
        LOGGER.trace("Connection closed event [{}]", e);
    }

}
