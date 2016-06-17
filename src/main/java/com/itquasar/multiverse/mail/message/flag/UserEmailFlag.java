/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.message.flag;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class UserEmailFlag implements EmailFlag {

    final String name;

    public UserEmailFlag(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
