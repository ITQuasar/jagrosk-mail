package com.itquasar.multiverse.lib.mail;

import java.util.Collections;
import java.util.Map;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface IMimeType {

    String getMimeType();

    String getMainType();

    String getSubType();

    default Map<String, String> getParameters() {
        return Collections.EMPTY_MAP;
    }

}
