package com.itquasar.multiverse.mail.server;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class SendStatus {

    public static final SendStatus SENDED = new SendStatus(true, false);
    public static final SendStatus INTERCEPTED = new SendStatus(false, true);

    private final boolean sended;
    /**
     * When a message is intercepted, it is not sended and don't throw
     * exception.
     */
    private final boolean intercepted;
    private final Throwable exception;

    /**
     * Sended or intercepted without throwing exception.
     *
     * @param sended
     * @param intercepted
     */
    public SendStatus(boolean sended, boolean intercepted) {
        this(sended, intercepted, null);
    }

    /**
     * Message not intercepted nor sended.
     *
     * @param exception
     */
    public SendStatus(Throwable exception) {
        this(false, false, exception);
    }

    private SendStatus(boolean sended, boolean intercepted, Throwable exception) {
        this.sended = sended;
        this.intercepted = intercepted;
        this.exception = exception;
    }

    public boolean isSended() {
        return sended;
    }

    public boolean isIntercepted() {
        return intercepted;
    }

    public boolean hasException() {
        return exception != null;
    }

    public Throwable getException() {
        return exception;
    }
}
