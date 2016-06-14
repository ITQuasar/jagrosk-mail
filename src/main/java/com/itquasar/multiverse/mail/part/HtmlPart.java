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
public class HtmlPart extends SinglePart<String> {

    public static HtmlPart fromPart(Part<String> part) {
        if (part.isMimeType(MimeTypes.TEXT_HTML)) {
            return new HtmlPart(part.getContentId().getId(), part.getDisposition(), part.getName(), part.getMimeType().getMimeType(), part.getContent());
        }
        throw new Error("Incompatible mime type: " + part.getMimeType() + " cant be used as " + MimeTypes.TEXT_PLAIN);
    }

    private HtmlPart(String contentId, Disposition disposition, String name, String mimeType, String content) {
        super(contentId, disposition, name, mimeType, content);
    }

    public HtmlPart(String contentId, Disposition disposition, String name, String content) {
        super(contentId, disposition, name, MimeTypes.TEXT_HTML.getMimeType(), content);
    }

    public HtmlPart(String name, Disposition disposition, String content) {
        super(name, disposition, MimeTypes.TEXT_HTML.getMimeType(), content);
    }

    public HtmlPart(String content) {
        super(MimeTypes.TEXT_HTML, content);
    }

}
