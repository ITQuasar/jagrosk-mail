package com.itquasar.multiverse.lib.mail.server;

import com.itquasar.multiverse.lib.mail.Email;
import com.itquasar.multiverse.lib.mail.util.Constants;
import com.itquasar.multiverse.lib.mail.util.ServerUtils;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class EmailFolder {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailFolder.class);

    private final String name;
    private final Folder folder;
    private final EmailServer outer;

    public EmailFolder(Store store, String name, boolean readAndWrite, final EmailServer outer) {
        this.outer = outer;
        try {
            this.name = name;
            this.folder = store.getFolder(name);
            folder.addConnectionListener(Constants.LoggerMailListener.CONNECTION);
            folder.addFolderListener(Constants.LoggerMailListener.FOLDER);
            folder.addMessageChangedListener(Constants.LoggerMailListener.MESSAGE_CHANGED);
            folder.addMessageCountListener(Constants.LoggerMailListener.MESSAGE_COUNT);
            int mode = readAndWrite ? Folder.READ_WRITE : Folder.READ_ONLY;
            folder.open(mode);
        } catch (MessagingException ex) {
            throw ServerUtils.logErrorAndGetNewException("Error opening folder " + name, ex, LOGGER);
        }
    }

    public void closeAndExpunge() {
        closeAndExpunge(true);
    }

    public void closeAndNotExpunge() {
        closeAndExpunge(false);
    }

    public void closeAndExpunge(boolean expunge) {
        try {
            this.folder.close(expunge);
        } catch (MessagingException ex) {
            throw ServerUtils.logErrorAndGetNewException("Error closing folder " + name, ex, LOGGER);
        }
    }

    public List<Email> receive() {
        return receive((e) -> e != null);
    }

    public List<Email> receive(Predicate<Email> filter) {
        try {
            return Arrays.asList(folder.getMessages()).stream().map((javax.mail.Message msg) -> new Email(msg)).filter(filter).collect(Collectors.toList());
        } catch (MessagingException ex) {
            throw ServerUtils.logErrorAndGetNewException("Error getting messages from folder " + name, ex, LOGGER);
        }
    }

    public Folder getFolder() {
        return folder;
    }

    @Override
    public String toString() {
        return "EmailFolder(" + name + ":" + folder + ')';
    }

}
