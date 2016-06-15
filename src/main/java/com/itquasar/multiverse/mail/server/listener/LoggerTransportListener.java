package com.itquasar.multiverse.mail.server.listener;

import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class LoggerTransportListener implements TransportListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerTransportListener.class);

    @Override
    public void messageDelivered(TransportEvent e) {
        LOGGER.trace("Message delivered event [{}]", e);
    }

    @Override
    public void messageNotDelivered(TransportEvent e) {
        LOGGER.trace("Message not delivered event [{}]", e);
    }

    @Override
    public void messagePartiallyDelivered(TransportEvent e) {
        LOGGER.trace("Message partially delivered event [{}]", e);
    }

}
