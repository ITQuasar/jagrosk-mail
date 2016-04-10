package com.itquasar.multiverse.mail.server;

import com.itquasar.multiverse.mail.message.Email;
import com.itquasar.multiverse.mail.exception.EmailServerException;
import com.itquasar.multiverse.mail.util.Constants;
import com.itquasar.multiverse.mail.util.ServerUtils;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class EmailServer implements Constants {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServer.class);

    private final Session session;
    /**
     * Should not be called directly. Must be called by {@link #getStore() } to
     * allow lazy connect.
     */
    private final Store _store;
    /**
     * Should not be called directly. Must be called by {@link #getTransport() }
     * to allow lazy connect.
     */
    private final Transport _transport;

    /**
     * Flag to auto connect services.
     */
    private final boolean autoConnectServices;

    /**
     * Creates a wrapper around {@link Store} and {@link Transport} mail
     * services using given java mail session. This constructor sets
     * {@link #autoConnectServices} to {@code true}.
     *
     * @param session Session to use.
     * @throws EmailServerException
     */
    public EmailServer(Session session) {
        this(session, true);
    }

    /**
     * Creates a wrapper around {@link Store} and {@link Transport} mail
     * services using given java mail session.
     *
     * @param session Session to use.
     * @param autoConnectServices If services (store and transport) should be
     * connected automatically when the getters are called.
     * @throws EmailServerException
     */
    public EmailServer(Session session, boolean autoConnectServices) {
        try {
            this.autoConnectServices = autoConnectServices;
            this.session = session;
            // store initialization
            this._store = session.getStore();
            _store.addConnectionListener(LoggerMailListener.CONNECTION);
            _store.addFolderListener(LoggerMailListener.FOLDER);
            _store.addStoreListener(LoggerMailListener.STORE);
            // transport initialization
            this._transport = session.getTransport();
            _transport.addConnectionListener(LoggerMailListener.CONNECTION);
            _transport.addTransportListener(LoggerMailListener.TRANSPORT);
        } catch (MessagingException | EmailServerException ex) {
            throw ServerUtils.logErrorAndGetNewException("Error connecting to mail store.", ex, LOGGER);
        }
    }

    /**
     * Connects the store service.
     *
     * @return {@code this} instance.
     * @throws EmailServerException
     */
    public EmailServer connectStore() {
        ServerUtils.connectService(_store, LOGGER);
        return this;
    }

    /**
     * Connects the transport service.
     *
     * @return {@code this} instance.
     * @throws EmailServerException
     */
    public EmailServer connectTransport() {
        ServerUtils.connectService(_transport, LOGGER);
        return this;
    }

    /**
     * Get store service. If {@link #autoConnectServices} is {@code  true}, call
     * {@link #connectStore()}.
     *
     * @return Store service instance, connected if {@link #autoConnectServices}
     * is {@code  true}, otherwise connection must be made using {@link #connectStore()
     * }.
     * @throws EmailServerException
     */
    public Store getStore() {
        if (autoConnectServices) {
            connectStore();
        }
        return _store;
    }

    /**
     * Get transport service. If {@link #autoConnectServices} is {@code  true},
     * call {@link #connectTransport() }.
     *
     * @return Transport service instance, connected if
     * {@link #autoConnectServices} is {@code  true}, otherwise connection must
     * be made using {@link #connectTransport() }.
     * @throws EmailServerException
     */
    public Transport getTransport() {
        if (autoConnectServices) {
            connectTransport();
        }
        return _transport;
    }

    /**
     * Closes store and transport services.
     *
     * @throws EmailServerException
     */
    public void close() {
        ServerUtils.closeService(LOGGER, _store, _transport);
    }

    /**
     * Open inbox folder using {@link #openFolderForReadAndWrite(String, boolean)
     * }.
     *
     * @param readAndWrite If must be read an write ({@code true}) or read only
     * ({@code false}).
     * @return The inbox folder.
     * @throws EmailServerException
     */
    public EmailFolder openInboxForReadAndWrite(boolean readAndWrite) {
        return openFolderForReadAndWrite("INBOX", readAndWrite);
    }

    /**
     * Open and return the given folder.
     *
     * @param name Folder name.
     * @param readAndWrite If must be read an write ({@code true}) or read only
     * ({@code false}).
     * @return The requested folder.
     * @throws EmailServerException
     */
    public EmailFolder openFolderForReadAndWrite(String name, boolean readAndWrite) {
        ServerUtils.serviceConnectedOrError(getStore());
        return new EmailFolder(getStore(), name, readAndWrite, this);
    }

    /**
     * Send email (see {@link #send(Message) }).
     *
     * @param email The email to send.
     * @throws EmailServerException
     */
    public void send(Email email) {
        send(email.toMessage(session));
    }

    /**
     * Send email (see {@link #send(Message, boolean) }).
     *
     * @param email The email to send.
     * @param interceptor The interceptor to allow or deny send email.
     * @throws EmailServerException
     */
    public void send(Email email, Interceptor<Email> interceptor) {
        send(email.toMessage(session), interceptor.allow(email));
    }

    /**
     * Send message (see {@link #send(Message, boolean) }).
     *
     * @param message The message to send.
     * @throws EmailServerException
     */
    public void send(Message message) {
        send(message, true);
    }

    /**
     * Send message (see {@link #send(Message, boolean) }).
     *
     * @param message The message to send.
     * @param interceptor The interceptor to allow or deny send email.
     * @throws EmailServerException
     */
    public void send(Message message, Interceptor<Message> interceptor) {
        send(message, interceptor.allow(message));
    }

    /**
     * Send message if allowed.
     *
     * @param message The message to send.
     * @param allow True if allowed, false otherwise.
     * @throws EmailServerException
     */
    private void send(Message message, boolean allow) {
        ServerUtils.serviceConnectedOrError(getTransport());
        if (allow) {
            try {
                getTransport().sendMessage(message, message.getAllRecipients());
            } catch (MessagingException ex) {
                ServerUtils.logErrorAndGetNewException("Error sending messge.", ex, LOGGER);
            }
        }
    }

    @Override
    public String toString() {
        return "EmailServer(" + "session=" + session + ", store=" + _store + ", transport=" + _transport + ')';
    }

}
