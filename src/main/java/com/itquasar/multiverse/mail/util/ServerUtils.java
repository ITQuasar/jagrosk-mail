package com.itquasar.multiverse.mail.util;

import com.itquasar.multiverse.mail.exception.EmailServerException;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.Service;
import org.slf4j.Logger;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public final class ServerUtils {

    private ServerUtils() {
    }

    public static EmailServerException logErrorAndGetNewException(String msg, Exception ex, Logger logger) {
        logger.error(msg, ex);
        return new EmailServerException(msg, ex);
    }

    public static void connectService(Service service, Logger logger) {
        if (!service.isConnected()) {
            try {
                service.connect();
            } catch (MessagingException ex) {
                throw logErrorAndGetNewException("Error connecting to mail store.", ex, logger);
            }
        }
    }

    public static void serviceConnectedOrError(Service service) {
        if (!service.isConnected()) {
            throw new EmailServerException("Service " + service.getClass().getSimpleName() + " not connected");
        }
    }

    public static void closeService(Logger logger, Service... services) {
        Map<Service, MessagingException> servicesExceptions = null;
        if (services != null) {
            for (Service service : services) {
                if (service.isConnected()) {
                    try {
                        service.close();
                    } catch (MessagingException ex) {
                        if (servicesExceptions == null) {
                            servicesExceptions = new HashMap<>();
                        }
                        servicesExceptions.put(service, ex);
                    }
                }
            }
        }
        if (servicesExceptions != null) {
            for (Service service : servicesExceptions.keySet()) {
                logger.error("Error closing service {}.", service.getClass().getSimpleName(), servicesExceptions.get(service));
            }
            throw new EmailServerException("One or more services failed to close. Services failed: "
                    + servicesExceptions.keySet()
                    + ". The throwned exceptions are logged in error .");
        }
    }
}
