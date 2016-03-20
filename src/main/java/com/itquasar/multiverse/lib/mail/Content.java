/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.lib.mail;

import com.itquasar.multiverse.lib.mail.part.Part;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface Content {

    List<Part> getAttachments();

    /**
     *
     * @return The content from the first text/html part
     */
    String getHtmlContent();

    List<Part> getHtmlImages();

    /**
     *
     * @return The first text/html part
     */
    Part<String> getHtmlPart();

    /**
     *
     * @return The content from the first text/plain part
     */
    String getTextContent();

    /**
     *
     * @return The first text/plain part
     */
    Part<String> getTextPart();

    boolean hasTextHtml();

    boolean hasImages();

    boolean hasTextPlain();

}
