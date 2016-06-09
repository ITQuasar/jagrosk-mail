package com.itquasar.multiverse.mail.api;

import com.itquasar.multiverse.mail.api.ID;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Guilherme I F L Weizenmann <guilherme at itquasar.com>
 */
public class IDTest {

    @Test
    public void testIdParseAndGet() {
        String idNotQuotedStr = "idValue",
                idQuotedStr = "<" + idNotQuotedStr + ">";
        ID idNotQuoted = new ID(idNotQuotedStr);
        ID idQuoted = new ID(idQuotedStr);

        assertEquals(idNotQuotedStr, idNotQuoted.getId());
        assertEquals("<" + idNotQuotedStr + ">", idNotQuoted.toRFCFormat());

        assertEquals(idNotQuotedStr, idQuoted.getId());
        assertEquals(idQuotedStr, idQuoted.toRFCFormat());
    }

}
