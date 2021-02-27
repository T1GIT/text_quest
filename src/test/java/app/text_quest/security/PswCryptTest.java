package app.text_quest.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PswCryptTest {

    @Test
    void crypt() {
        System.out.println(PswCrypt.crypt("password"));
    }

    @Test
    void check() {
        String hash = PswCrypt.crypt("password");
        assertTrue(PswCrypt.check("password", hash));
        assertFalse(PswCrypt.check("wrongPassword", hash));
    }
}