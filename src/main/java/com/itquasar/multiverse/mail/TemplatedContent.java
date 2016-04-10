package com.itquasar.multiverse.mail;

import com.itquasar.multiverse.mail.part.Attachment;
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
