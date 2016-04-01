package com.itquasar.multiverse.lib.mail.server.listener;

import javax.mail.event.FolderEvent;
import javax.mail.event.FolderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class LoggerFolderListenner implements FolderListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerFolderListenner.class);

    @Override
    public void folderCreated(FolderEvent e) {
        LOGGER.trace("Folder created event [{}]", e);
    }

    @Override
    public void folderDeleted(FolderEvent e) {
        LOGGER.trace("Folder deleted event [{}]", e);
    }

    @Override
    public void folderRenamed(FolderEvent e) {
        LOGGER.trace("Folder renamed event [{}]", e);
    }

}
