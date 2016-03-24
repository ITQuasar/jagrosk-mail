package com.itquasar.multiverse.lib.mail.part;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public enum MimeType {
    TEXT("text", "*"),
    TEXT_PLAIN("text", "plain"),
    TEXT_HTML("text", "html"),
    MESSAGE_RC822("message", "rfc822"),
    IMAGE("image", "*"),
    MULTIPART("multipart", "*"),
    MULTIPART_ALTERNATIVE("multipart", "alternative"),
    MULTIPART_MIXED("multipart", "mixed"),
    MULTIPART_RELATED("multipart", "related");

    private final String mainType;
    private final String subType;
    private final String mimeType;

    private MimeType(String mainType, String subType) {
        this.mainType = mainType;
        this.subType = subType;
        this.mimeType = mainType + "/" + subType;
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

}
