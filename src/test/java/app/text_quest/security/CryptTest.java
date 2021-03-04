package app.text_quest.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CryptTest {

    @Test
    void crypt() {
        System.out.println(Crypt.crypt("password"));
    }

    @Test
    void manyCrypt() {
        for (int i = 0; i < 100; i++) {
            Crypt.crypt("password" + (i * 1234));
        }
    }

    @Test
    void check() {
        String hash = Crypt.crypt("password");
        assertTrue(Crypt.check("password", hash));
        assertFalse(Crypt.check("wrongPassword", hash));
    }
}