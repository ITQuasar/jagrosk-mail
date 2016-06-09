package com.itquasar.multiverse.mail.transformer;

import com.itquasar.multiverse.mail.ImmutableEmail;
import com.itquasar.multiverse.mail.part.Part;
import com.itquasar.multiverse.mail.util.Constants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class WebEmail extends ImmutableEmail {

    public WebEmail(ImmutableEmail email) {
        super(email.getEnvelope(), email.getContent());
    }

    public String getTextHtmlCidByBase64() {
        String html = Constants.EMPTY_STRING;
        if (getContent().hasTextHtml()) {
            html = getContent().getHtmlContent();

            for (Part image : getContent().getHtmlImages()) {
                try {
//                    if (!image.getName().isEmpty()) {
                    File file = File.createTempFile("img", "tmp");
                    file.deleteOnExit();

                    //File file = new File("/tmp/" + image.getName());
                    //FileOutputStream fos = new FileOutputStream(file);
                    ByteArrayOutputStream fos = new ByteArrayOutputStream();
                    InputStream in = (InputStream) image.getContent();

                    byte[] buffer = new byte[1024];
                    while (true) {

                        int r = in.read(buffer);
                        if (r == -1) {
                            break;
                        }
                        fos.write(buffer, 0, r);

                    }
//                    }

                    String cleanID = image.getContentId().getId();
                    html = html.replace(
                            "cid:" + cleanID,
                            //"/" + file.getName()
                            "data:"
                            + (image.getMimeType().getMimeType())
                            + ";base64," + Base64.getEncoder().encodeToString(fos.toByteArray())
                    );
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        }
        return html;
    }

}
