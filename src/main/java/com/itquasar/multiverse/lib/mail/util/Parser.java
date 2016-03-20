package com.itquasar.multiverse.lib.mail.util;

import com.itquasar.multiverse.lib.mail.content.ImmutableContent;
import com.itquasar.multiverse.lib.mail.envelope.ImmutableEnvelope;
import com.itquasar.multiverse.lib.mail.exception.EmailException;
import com.itquasar.multiverse.lib.mail.part.Attachment;
import com.itquasar.multiverse.lib.mail.part.Inline;
import com.itquasar.multiverse.lib.mail.part.Multipart;
import com.itquasar.multiverse.lib.mail.part.Part;
import com.itquasar.multiverse.lib.mail.part.SinglePart;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimePart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(Parser.class);

//    private static boolean isMime(Part part, Part.Mime mimeToMatch) {
//        return isMimeEquals(part.getMimeType(), mimeToMatch);
//    }
//
//    private static boolean isMime(javax.mail.Part part, Part.Mime mimeToMatch) throws MessagingException {
//        return isMimeEquals(part.getContentType(), mimeToMatch);
//    }
//
//    private static boolean isMimeEquals(String mime, Part.Mime mimeToMatch) {
//        return isSameMime(mime, mimeToMatch.getMimeType());
//    }
//
//    public static boolean isSameMime(String mimeType, Part.Mime mimeToMatch) {
//        return isSameMime(mimeToMatch.getMimeType(), mimeType);
//    }
//
    public static boolean isSameMime(String mimeType, Part.Mime searchMime) {
        return isSameMime(mimeType, searchMime.getMimeType());
    }

    public static boolean isSameMime(String mimeType, String searchMime) {
        LOGGER.trace("comparing [" + mimeType + "] with [" + searchMime + "]");
        mimeType = sanitizeMimeToPrefix(mimeType);
        searchMime = sanitizeMimeToPrefix(searchMime);
        boolean isMimeType
                = !mimeType.isEmpty()
                && mimeType.toLowerCase().startsWith(searchMime);
        LOGGER.trace("[" + mimeType + "] starts with [" + searchMime + "]? " + isMimeType);
        return isMimeType;
    }

    private static String sanitizeMimeToPrefix(String mimeType) {
        return mimeStarToMimePrefix(mimeWithoutExtraParameters(mimeType));
    }

    private static String mimeStarToMimePrefix(String mimeType) {
        return mimeType.endsWith("*") ? mimeType.substring(0, mimeType.length() - 1) : mimeType;
    }

    private static String mimeWithoutExtraParameters(String mimeType) {
        return mimeType.contains(";") ? mimeType.substring((0), mimeType.indexOf(";")).trim() : mimeType;
    }

    private static Part parseParts(javax.mail.Part part) throws MessagingException, IOException {
        return parseParts(part, 0);
    }

    private static Part parseParts(javax.mail.Part part, int depth) throws MessagingException, IOException {
        String tab = "";
        while (tab.length() < depth * 4) {
            tab += "    ";
        }
        LOGGER.trace(tab + "- " + part.getContentType());

        String contentId = Constants.EMPTY_STRING;
        if (MimePart.class.isInstance(part)) {
            MimePart mp = MimePart.class.cast(part);
            contentId = mp.getContentID();
        }

        if (part.getContentType().toLowerCase().contains("format=flowed")) {
            if (part.isMimeType(Part.Mime.TEXT_PLAIN.getMimeType())) {
                return new SinglePart(
                        contentId,
                        Part.Disposition.evaluate(part.getDisposition()),
                        part.getContentType(),
                        String.class.cast(part.getContent())
                );
            } else if (part.isMimeType(Part.Mime.TEXT_HTML.getMimeType())) {
                return new SinglePart(
                        contentId,
                        Part.Disposition.evaluate(part.getDisposition()),
                        part.getContentType(),
                        String.class.cast(part.getContent())
                );
            }
        } else if (part.isMimeType(Part.Mime.IMAGE.getMimeType()) && Part.Disposition.evaluate(part.getDisposition()) != Part.Disposition.ATTACHMENT) {
            MimePart mp = (MimePart) part;
            return new Inline(mp.getContentID(), mp.getFileName(), mp.getContentType(), mp.getContent());
        } else if (part.isMimeType(Part.Mime.MULTIPART.getMimeType())) {
            List<Part<?>> subParts = new LinkedList<>();
            javax.mail.Multipart mp;
            if (!javax.mail.Multipart.class.isInstance(part)) {
                mp = (javax.mail.Multipart) part.getContent();
            } else {
                mp = (javax.mail.Multipart) part;
            }
            for (int i = 0; i < mp.getCount(); i++) {
                subParts.add(parseParts(mp.getBodyPart(i)));
            }
            return new Multipart(part.getContentType(), subParts);
        }

        switch (Part.Disposition.evaluate(part.getDescription())) {
            case INLINE:
                return new Inline(contentId, part.getFileName(), part.getContentType(), part.getContent());
            case ATTACHMENT:
            default:
                return new Attachment(contentId, part.getFileName(), part.getContentType(), part.getContent());
        }
    }

//    public static GenericPart buildPart(javax.mail.Part part) {
//        GenericPart result = null;
//        try {
//            String mime = part.getContentType();
//            String name = part.getFileName();
//            Object content = part.getContent();
//            String contentId = Constants.EMPTY_STRING;
//            Part.Disposition disposition = Part.Disposition.evaluate(part.getDisposition());
//
//            if (part.getClass().isInstance(MimePart.class
//            )) {
//                contentId = MimePart.class
//                        .cast(part).getContentID();
//            }
//            switch (disposition) {
//                case ATTACHMENT:
//                    result = new Attachment(contentId, name, mime, content);
//                    break;
//                case INLINE:
//                    result = new Inline(contentId, name, mime, content);
//                    break;
//                case NONE:
//                    result = new GenericPart(contentId, disposition, name, mime, content);
//                    break;
//            }
//        } catch (MessagingException | IOException ex) {
//            LOGGER.error("Error building message part.", ex);
//            throw new EmailException("Could not build Attacment from message Part", ex);
//        }
//        return result;
//    }
    public static ImmutableEnvelope parseMessageEnvelope(Message message) {
        LOGGER.debug("Parsing message envelope...");
        try {
            InternetAddress[] from = (InternetAddress[]) message.getFrom();
            InternetAddress[] replyTo = (InternetAddress[]) message.getReplyTo();
            InternetAddress[] to = (InternetAddress[]) message.getRecipients(Message.RecipientType.TO);
            InternetAddress[] cc = (InternetAddress[]) message.getRecipients(Message.RecipientType.CC);
            InternetAddress[] bcc = (InternetAddress[]) message.getRecipients(Message.RecipientType.BCC);

            String subject = Utils.emptyOnNull(message.getSubject());
            LOGGER.debug("...message envelope parsed.");
            return new ImmutableEnvelope(from, replyTo, to, cc, bcc, subject);
        } catch (MessagingException ex) {
            LOGGER.error("Error parsing javax.mail.Message [{}]", message, ex);
            throw new EmailException("Could not build envelope from Message", ex);
        }
    }

    public static ImmutableContent parseMessageContent(Message message) {
        try {
            LOGGER.debug("Parsing message content...");

            Part<?> rootPart = Parser.parseParts(message);

            Part<String> textPart = Constants.EMPTY_TEXT_PART;
            Part<String> htmlPart = Constants.EMPTY_HTML_PART;
            final List<Part> images = new LinkedList<>();
            final List<Part> attachs = new LinkedList<>();

            if (rootPart.isMimeType(Part.Mime.MULTIPART_MIXED)) {
                Tuple<Part<?>, List<Part<?>>> tuple = parseMultipartMixed(rootPart);
                rootPart = tuple.fst().hasContent() ? tuple.fst() : rootPart;
                attachs.addAll(tuple.snd());
            }
            if (rootPart.isMimeType(Part.Mime.MULTIPART_ALTERNATIVE)) {
                Tuple<Part<String>, Part<?>> tuple = parseMultipartAlternative(rootPart);
                textPart = tuple.fst();
                rootPart = tuple.snd().hasContent() ? tuple.snd() : rootPart;
            }
            if (rootPart.isMimeType(Part.Mime.MULTIPART_RELATED)) {
                Tuple<Part<String>, List<Part<?>>> tuple = parseMultipartRelated(rootPart);
                rootPart = tuple.fst().hasContent() ? tuple.fst() : rootPart;
                images.addAll(tuple.snd());
            }
            if (rootPart.isMimeType(Part.Mime.TEXT_PLAIN)) {
                textPart = (Part<String>) rootPart;
            } else if (rootPart.isMimeType(Part.Mime.TEXT_HTML)) {
                htmlPart = (Part<String>) rootPart;
            }
            List<Part> attachsFiltered = attachs.stream()
                    .filter((p) -> p.hasContent())
                    .collect(Collectors.toList());
            LOGGER.debug("...message content parsed.");
            return new ImmutableContent(textPart, htmlPart, images, attachsFiltered);
        } catch (MessagingException | IOException ex) {
            LOGGER.error("Error parsing message content.", ex);
            throw new EmailException("Could not build content from Message", ex);
        }
    }

    private static Tuple<Part<?>, List<Part<?>>> parseMultipartMixed(Part<?> rootPart) {
        Part<?> mainContent = (Part<String>) rootPart.getParts().stream()
                .filter((part)
                        -> part.isMimeType(Part.Mime.TEXT) || part.isMimeType(Part.Mime.MULTIPART)
                )
                .findFirst()
                .get();
        List<Part<?>> attachs = new LinkedList<>();
//        rootPart.getParts().stream()
//                .filter((part)
//                        -> !(part.isMimeType(Part.Mime.TEXT) || part.isMimeType(Part.Mime.MULTIPART))
//                )
//                .forEach((part) -> attachs.add(part));
        attachs.addAll(rootPart.getParts());
        LOGGER.trace("LIST:     " + attachs.size() + " " + attachs);
        attachs.remove(mainContent);
        LOGGER.trace("LIST REM: " + attachs.size() + " " + attachs);
        return new Tuple<>(mainContent, attachs);
    }

    private static Tuple<Part<String>, Part<?>> parseMultipartAlternative(Part<?> rootPart) {
        Optional<Part<?>> opt;
        opt = rootPart.getParts().stream()
                .filter((part) -> part.isMimeType(Part.Mime.TEXT_PLAIN))
                .findFirst();
        Part<String> textPart = (Part<String>) opt.orElse(Constants.EMPTY_PART);
        opt = rootPart.getParts().stream()
                .filter((part) -> part.isMimeType(Part.Mime.MULTIPART_RELATED))
                .findFirst();
        Part<?> relatedPart = opt.orElse(Constants.EMPTY_PART);
        return new Tuple<>(textPart, relatedPart);
    }

    private static Tuple<Part<String>, List<Part<?>>> parseMultipartRelated(Part<?> rootPart) {
        Optional<Part<?>> opt = rootPart.getParts().stream()
                .filter((part) -> part.isMimeType(Part.Mime.TEXT))
                .findFirst();
        Part<String> htmlPart = (Part<String>) opt.orElse(Constants.EMPTY_PART);
        List<Part<?>> images = new LinkedList<>();
        rootPart.getParts().stream()
                .filter((part) -> part.isMimeType(Part.Mime.IMAGE))
                .forEach((part) -> images.add(part));
        return new Tuple<>(htmlPart, images);
    }

    private static class Tuple<A, B> {

        private final A first;
        private final B second;

        public Tuple(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A fst() {
            return first;
        }

        public B snd() {
            return second;
        }

    }

}
