package com.itquasar.multiverse.lib.mail.util;

import com.itquasar.multiverse.lib.mail.EmailContact;
import com.itquasar.multiverse.lib.mail.part.MimeTypes;
import com.itquasar.multiverse.lib.mail.part.Part;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public final class ClientUtils {

    private ClientUtils() {
    }

    public static List<EmailContact> emailContactToList(EmailContact... contacts) {
        List<EmailContact> list = new LinkedList<>();
        if (contacts != null) {
            list.addAll(Arrays.asList(contacts));
        }
        return list;
    }

    public static MimeBodyPart partToMimeBodyPart(Part part) throws MessagingException {
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContentID(part.getContentId());
        bodyPart.setFileName(part.getName());
        bodyPart.setContent(part.getContent(), part.getMimeType());
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

    public static MimeMultipart buildeMultipart(String subType, List<Part> parts) throws MessagingException {
        parts = FunctionUtils.defaultOnNull(parts, Constants.NO_PARTS);
        MimeMultipart multipart = new MimeMultipart(subType);
        for (Part part : parts) {
            multipart.addBodyPart(partToMimeBodyPart(part));
        }
        return multipart;
    }
}
