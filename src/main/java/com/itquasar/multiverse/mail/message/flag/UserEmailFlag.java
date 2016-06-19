/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message.flag;

import java.util.Objects;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class UserEmailFlag implements EmailFlag {

    public static UserEmailFlag of(String name) {
        return new UserEmailFlag(name);
    }

    final String name;

    public UserEmailFlag(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserEmailFlag other = (UserEmailFlag) obj;
        return Objects.equals(this.name, other.getName());
    }

    @Override
    public String toString() {
        return WHITE_FLAG + name;
    }

}
