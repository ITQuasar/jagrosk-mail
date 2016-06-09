package com.itquasar.multiverse.mail.part;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public enum MimeTypes implements MimeType {
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

    private MimeTypes(String mainType, String subType) {
        this.mainType = mainType;
        this.subType = subType;
        this.mimeType = mainType + "/" + subType;
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

}
