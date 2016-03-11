package com.itquasar.multiverse.lib.mail.part;

import com.itquasar.multiverse.lib.mail.util.Constants;
import java.util.List;

/**
 *
 * {@link #getContent() } returns same as {@link #getParts() }.
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class Multipart extends GenericPart< List<Part>> {

    public static Disposition MULTIPART_DISPOSITION = Disposition.NONE;

    public Multipart(String mimeType, List<Part> content) {
        super(Constants.EMPTY_STRING, MULTIPART_DISPOSITION, Constants.EMPTY_STRING, mimeType, content, content);
    }

}
