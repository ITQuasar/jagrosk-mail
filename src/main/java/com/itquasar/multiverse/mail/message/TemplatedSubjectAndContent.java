package com.itquasar.multiverse.mail.message;

import com.itquasar.multiverse.mail.part.Inline;
import com.itquasar.multiverse.mail.util.Constants;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public interface TemplatedSubjectAndContent {

    default String getSubject() {
        return Constants.EMPTY_STRING;
    }

    default String getTextContent() {
        return Constants.EMPTY_STRING;
    }

    default String getHtmlContent() {
        return Constants.EMPTY_STRING;
    }

    default List<Inline<?>> getHtmlImages() {
        return Collections.emptyList();
    }
}
