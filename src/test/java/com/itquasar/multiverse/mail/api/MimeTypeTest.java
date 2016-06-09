package com.itquasar.multiverse.mail.api;

import com.itquasar.multiverse.mail.api.MimeType;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class MimeTypeTest {

    @Test
    public void testTextPlainWithCharsetAndFlowedParams() {
        String str = "text/plain; charset=utf-8; format=flowed";
        MimeType mime = MimeType.build(str);
        assertEquals("text", mime.getMainType());
        assertEquals("plain", mime.getSubType());
        assertEquals("text/plain", mime.getMimeType());
        assertEquals(2, mime.getParameters().size());
        assertEquals(str, mime.toString());
    }

    @Test
    public void textPlainWithCharsetParam() {
        String str = "text/html; charset=utf-8";
        MimeType mime = MimeType.build(str);
        assertEquals("text", mime.getMainType());
        assertEquals("html", mime.getSubType());
        assertEquals("text/html", mime.getMimeType());
        assertEquals(1, mime.getParameters().size());
        assertEquals(str, mime.toString());
    }

    @Test
    public void testImagePng() {
        String str = "image/png";
        MimeType mime = MimeType.build(str);
        assertEquals("image", mime.getMainType());
        assertEquals("png", mime.getSubType());
        assertEquals("image/png", mime.getMimeType());
        assertEquals(0, mime.getParameters().size());
        assertEquals(str, mime.toString());
    }
}
