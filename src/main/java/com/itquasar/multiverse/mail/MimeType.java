package com.itquasar.multiverse.mail;

import com.itquasar.multiverse.mail.util.Constants;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface MimeType {

    String getMimeType();

    String getMainType();

    String getSubType();

    default Map<String, String> getParameters() {
        return Collections.emptyMap();
    }

    static final String PARAMS_SEP = "; ";

    static MimeType build(String mimeTypeString) {
        String[] parts = mimeTypeString != null
                ? mimeTypeString.split(";")
                : new String[0];
        String mimeType = parts.length >= 1 ? parts[0] : Constants.EMPTY_STRING;
        String[] mainAndSub = !mimeType.isEmpty() ? mimeType.split("/") : new String[0];
        String mainType = mainAndSub.length >= 1 ? mainAndSub[0].trim() : Constants.EMPTY_STRING;
        String subType = mainAndSub.length >= 2 ? mainAndSub[1].trim() : Constants.EMPTY_STRING;
        Map<String, String> parameters = new LinkedHashMap<>();
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
        return new ImmutableMimeType(mainType, subType, mimeType, parameters);
    }

    static String parametersToString(Map<String, String> parameters) {
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

    public static class ImmutableMimeType implements MimeType {

        private final String mainType;
        private final String subType;
        private final String mimeType;
        private final Map<String, String> parameters;

        public ImmutableMimeType(String mainType, String subType, String mimeType,
                Map<String, String> parameters) {
            this.mainType = mainType;
            this.subType = subType;
            this.mimeType = mimeType;
            this.parameters = Collections.unmodifiableMap(parameters);
        }

        public String getMimeType() {
            return mimeType;
        }

        public String getMainType() {
            return mainType;
        }

        public String getSubType() {
            return subType;
        }

        public Map<String, String> getParameters() {
            return parameters;
        }

        @Override
        public String toString() {
            return mimeType + (parameters.size() > 0 ? parametersToString(parameters) : Constants.EMPTY_STRING);
        }

    }

}
