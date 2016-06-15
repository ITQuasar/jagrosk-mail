package com.itquasar.multiverse.mail.server.listener;

import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class LoggerMessageCountListener implements MessageCountListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerMessageCountListener.class);

    @Override
    public void messagesAdded(MessageCountEvent e) {
        LOGGER.trace("Message added event [{}]", e);
    }

    @Override
    public void messagesRemoved(MessageCountEvent e) {
        LOGGER.trace("Message removed event [{}]", e);
    }

}
