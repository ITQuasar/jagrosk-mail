/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.mail.part;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class TextPart extends SinglePart<String> {

    public static TextPart fromPart(Part<String> part) {
        if (part.isMimeType(MimeTypes.TEXT_PLAIN)) {
            return new TextPart(part.getContentId().getId(), part.getDisposition(), part.getName(), part.getMimeType().getMimeType(), part.getContent());
        }
        throw new Error("Incompatible mime type: " + part.getMimeType() + " cant be used as " + MimeTypes.TEXT_PLAIN);
    }

    private TextPart(String contentId, Disposition disposition, String name, String mimeType, String content) {
        super(contentId, disposition, name, mimeType, content);
    }

    public TextPart(String contentId, Disposition disposition, String name, String content) {
        super(contentId, disposition, name, MimeTypes.TEXT_PLAIN.getMimeType(), content);
    }

    public TextPart(String name, Disposition disposition, String content) {
        super(name, disposition, MimeTypes.TEXT_PLAIN.getMimeType(), content);
    }

    public TextPart(String content) {
        super(MimeTypes.TEXT_PLAIN, content);
    }

}
