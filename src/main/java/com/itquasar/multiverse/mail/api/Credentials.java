package com.itquasar.multiverse.mail.api;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class Credentials extends Authenticator {

    private final String username;
    private final String password;
    private final PasswordAuthentication authentication;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
        this.authentication = new PasswordAuthentication(username, password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return authentication;
    }

    public Authenticator asAuthenticator() {
        return Authenticator.class.cast(this);
    }

    @Override
    public String toString() {
        return "Credentials{" + "username=" + username + '}';
    }

}
