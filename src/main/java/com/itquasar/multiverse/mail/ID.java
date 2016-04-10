package com.itquasar.multiverse.mail;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class ID {

    private final String id;

    public ID(String id) {
        id = id.startsWith("<") ? id.substring(1) : id;
        id = id.endsWith(">") ? id.substring(0, id.length() - 1) : id;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String toRFCFormat() {
        return "<" + id + ">";
    }

    @Override
    public String toString() {
        return "ID(" + id + ")";
    }

}
