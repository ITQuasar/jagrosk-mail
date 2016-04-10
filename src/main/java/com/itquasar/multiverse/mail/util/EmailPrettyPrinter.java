package com.itquasar.multiverse.mail.util;

import com.itquasar.multiverse.mail.api.Content;
import com.itquasar.multiverse.mail.api.Envelope;
import com.itquasar.multiverse.mail.message.Email;
import com.itquasar.multiverse.mail.part.Part;
import java.util.List;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class EmailPrettyPrinter {

    private static final String EOL = "\n";//System.getProperty("line.seprator");
    private static final String SEPARATOR = "---------------------------------";

    private final Email email;
    private final StringBuilder stringBuilder = new StringBuilder();

    public EmailPrettyPrinter(Email email) {
        this.email = email;
        printEnvelope();
        printContent();
    }

    private void printEnvelope() {
        Envelope envelope = email.getEnvelope();
        stringBuilder
                .append("From:          ")
                .append(envelope.getFrom().toString())
                .append(EOL)
                .append("Reply-To:      ")
                .append(envelope.getReplyTo().toString())
                .append(EOL)
                .append("To:            ")
                .append(envelope.getTo().toString())
                .append(EOL)
                .append("Cc:            ")
                .append(envelope.getCc().toString())
                .append(EOL)
                .append("Bcc:           ")
                .append(envelope.getBcc().toString())
                .append(EOL)
                .append("Subject:       ")
                .append(envelope.getSubject())
                .append(EOL);
    }

    private void printContent() {
        Content content = email.getContent();
        stringBuilder
                .append("Text ").append(SEPARATOR).append(EOL)
                .append(content.getTextContent())
                .append(EOL).append(SEPARATOR).append(EOL)
                .append("Html ").append(SEPARATOR).append(EOL)
                .append(content.getHtmlContent())
                .append(EOL).append(SEPARATOR).append(EOL)
                .append("Html images:   ")
                .append(EOL)
                .append(printParts(content.getHtmlImages(), 0)).append(EOL)
                .append("Attachments:   ").append(EOL)
                .append(printParts(content.getAttachments(), 0))
                .append(EOL);
    }

    private String printParts(List<? extends Part> parts, int depth) {
        StringBuilder aux = new StringBuilder();
        while (aux.length() < depth) {
            aux.append(" ");
        }
        aux.append("- ");
        final String tab = aux.toString();
        final StringBuilder result = new StringBuilder();
        parts.stream().map((part) -> {
            result.append(tab).append(part.toString()).append(EOL);
            return part;
        }).filter((part) -> (!part.getParts().isEmpty())).forEach((part) -> {
            result.append(printParts(part.getParts(), depth + 4));
        });
        ;
        return result.toString();
    }

    @Override
    public String toString() {
        return this.stringBuilder.toString();
    }

}
