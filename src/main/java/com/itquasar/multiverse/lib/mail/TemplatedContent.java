package com.itquasar.multiverse.lib.mail;

import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface TemplatedContent {

    String getSubject();

    String getTextContent();

    String getHtmlContent();

    List<Attachment> getHtmlImages();
}
