/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itquasar.multiverse.lib.mail.util;

import com.itquasar.multiverse.lib.mail.Email;
import com.itquasar.multiverse.lib.mail.MessagePart;
import com.itquasar.multiverse.lib.mail.exception.EmailException;
import com.itquasar.multiverse.lib.mail.part.Attachment;
import com.itquasar.multiverse.lib.mail.part.GenericPart;
import com.itquasar.multiverse.lib.mail.part.Inline;
import com.itquasar.multiverse.lib.mail.part.Multipart;
import com.itquasar.multiverse.lib.mail.part.Part;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimePart;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class Parser {

    public static List<Part> parseParts(javax.mail.Part p) throws MessagingException, IOException {
        return parseParts(p, new LinkedList<>());
    }

    private static List<Part> parseParts(javax.mail.Part p, List<Part> parts) throws MessagingException, IOException {
        // chek if the content is multipert
        if (p.isMimeType("multipart/*")) {
            javax.mail.Multipart mp = (javax.mail.Multipart) p.getContent();
            int count = mp.getCount();
            List<Part> subParts = new LinkedList<>();
            for (int i = 0; i < count; i++) {
                parseParts(mp.getBodyPart(i), subParts);
            }
            Multipart multipart = new Multipart(mp.getContentType(), subParts);
            parts.add(multipart);
        } //check if the content is a nested message
        else if (p.isMimeType("message/rfc822")) {
            Message message = (Message) p.getContent();
            parts.add(new MessagePart(message.getFileName(), Part.Disposition.evaluate(message.getDisposition()),
                    message.getContentType(), new Email(message)));
        } else {
            parts.add(buildPart(p));
        }
        return parts;
    }

    public static GenericPart buildPart(javax.mail.Part part) {
        GenericPart result = null;
        try {
            String mime = part.getContentType();
            String name = part.getFileName();
            Object content = part.getContent();
            String contentId = Constants.EMPTY_STRING;
            Part.Disposition disposition = Part.Disposition.evaluate(part.getDisposition());
            if (part.getClass().isInstance(MimePart.class)) {
                contentId = MimePart.class.cast(part).getContentID();
            }
            switch (disposition) {
                case ATTACHMENT:
                    result = new Attachment(contentId, name, mime, content);
                    break;
                case INLINE:
                    result = new Inline(contentId, name, mime, content);
                    break;
                case NONE:
                    result = new GenericPart(contentId, disposition, name, mime, content);
                    break;
            }
        } catch (MessagingException | IOException ex) {
            Logger.getLogger(Attachment.class.getName()).log(Level.SEVERE, null, ex);
            throw new EmailException("Could not build Attacment from message Part", ex);
        }
        return result;
    }
}
