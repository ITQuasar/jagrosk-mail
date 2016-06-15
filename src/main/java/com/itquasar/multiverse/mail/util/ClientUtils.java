package com.itquasar.multiverse.mail.util;

import com.itquasar.multiverse.mail.part.MimeTypes;
import com.itquasar.multiverse.mail.part.Part;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public final class ClientUtils {

    private static final String EMAIL_PATTERN_STR
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_PATTERN_STR);

    private ClientUtils() {
    }

    public static boolean isEmailValid(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static MimeBodyPart partToMimeBodyPart(Part part) throws MessagingException {
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContentID(part.getContentId().getId());
        bodyPart.setFileName(part.getName());
        bodyPart.setContent(part.getContent(), part.getMimeType().getMimeType());
        bodyPart.setDisposition(part.getDisposition().value());
        return bodyPart;
    }

    public static MimeBodyPart buildMultipartBody(MimeTypes mimeType, Multipart multipart) throws MessagingException {
        return buildMultipartBody(mimeType.getMimeType(), multipart);
    }

    public static MimeBodyPart buildMultipartBody(String mimeType, Multipart multipart) throws MessagingException {
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(multipart, mimeType);
        return bodyPart;
    }

    public static MimeMultipart buildeMultipart(String subType, Part... parts) throws MessagingException {
        return buildeMultipart(subType, Arrays.asList(parts));
    }

    public static MimeMultipart buildeMultipart(String subType, List<? extends Part> parts) throws MessagingException {
        parts = FunctionUtils.defaultOnNull(parts, Constants.NO_PARTS);
        MimeMultipart multipart = new MimeMultipart(subType);
        for (Part part : parts) {
            multipart.addBodyPart(partToMimeBodyPart(part));
        }
        return multipart;
    }
}
