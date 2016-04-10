package com.itquasar.multiverse.mail.server.listener;

import javax.mail.event.MessageChangedEvent;
import javax.mail.event.MessageChangedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class LoggerMessageChangedListener implements MessageChangedListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerMessageChangedListener.class);

    @Override
    public void messageChanged(MessageChangedEvent e) {
        LOGGER.trace("Message changed event [{}]", e);
    }

}
