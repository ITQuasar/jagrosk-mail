package com.itquasar.multiverse.lib.mail;

import com.itquasar.multiverse.lib.mail.util.Constants;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class MimeType implements IMimeType {

    private static final String PARAMS_SEP = "; ";

    private final String mainType;
    private final String subType;
    private final String mimeType;
    private final Map<String, String> parameters = new LinkedHashMap<>();

    public MimeType(String mimeTypeString) {
        String[] parts = mimeTypeString != null
                ? mimeTypeString.split(";")
                : new String[0];
        this.mimeType = parts.length >= 1 ? parts[0] : Constants.EMPTY_STRING;
        String[] mainAndSub = !this.mimeType.isEmpty() ? this.mimeType.split("/") : new String[0];
        this.mainType = mainAndSub.length >= 1 ? mainAndSub[0].trim() : Constants.EMPTY_STRING;
        this.subType = mainAndSub.length >= 2 ? mainAndSub[1].trim() : Constants.EMPTY_STRING;
        for (int i = 1; i < parts.length; i++) {
            String parameter = parts[i];
            String[] paramPair = parameter.split("=", 1);
            if (paramPair.length > 0) {
                parameters.put(
                        paramPair[0].trim(),
                        paramPair.length > 1 ? paramPair[1].trim() : Constants.EMPTY_STRING
                );
            }
        }
    }

    public MimeType(String mainType, String subType, String mimeType) {
        this.mainType = mainType;
        this.subType = subType;
        this.mimeType = mimeType;
    }

    @Override
    public String getMimeType() {
        return mimeType;
    }

    @Override
    public String getMainType() {
        return mainType;
    }

    @Override
    public String getSubType() {
        return subType;
    }

    @Override
    public Map<String, String> getParameters() {
        return parameters;
    }

    private String parametersToString() {
        StringBuilder buffy = new StringBuilder();
        for (String key : parameters.keySet()) {
            buffy.append(PARAMS_SEP).append(key);
            String val = parameters.get(key);
            if (!val.isEmpty()) {
                buffy.append("=").append(val);
            }
        }
        return buffy.toString();
    }

    @Override
    public String toString() {
        return mimeType + (parameters.size() > 0 ? parametersToString() : Constants.EMPTY_STRING);
    }

}
