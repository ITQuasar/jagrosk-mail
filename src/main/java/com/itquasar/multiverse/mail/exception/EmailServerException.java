/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.exception;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class EmailServerException extends EmailException {

    public EmailServerException(String message) {
        super(message);
    }

    public EmailServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailServerException(Throwable cause) {
        super(cause);
    }

    public EmailServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
