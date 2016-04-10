package com.itquasar.multiverse.mail.api;

/**
 * Represents an RFC Message/Part ID.
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class ID {

    /**
     * ID value.
     */
    private final String id;

    /**
     * Builds ID object based on id value. This constructor checks the format
     * and parse if needed.
     *
     * @param id
     */
    public ID(String id) {
        id = id.startsWith("<") ? id.substring(1) : id;
        id = id.endsWith(">") ? id.substring(0, id.length() - 1) : id;
        this.id = id;
    }

    /**
     *
     * @return The ID value.
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return The ID in RFC format.
     */
    public String toRFCFormat() {
        return "<" + id + ">";
    }

    @Override
    public String toString() {
        return "ID(" + id + ")";
    }

}
