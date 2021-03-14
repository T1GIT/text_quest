package app.text_quest.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class HashTest {

    @Test
    void crypt() {
        System.out.println(Hash.crypt("password"));
    }

    @Test
    void manyCrypt() {
        for (int i = 0; i < 100; i++) {
            Hash.crypt("password" + (i * 1234));
        }
    }

    @Test
    void check() {
        String hash = Hash.crypt("password");
        System.out.println(hash);
        System.out.println(hash.length());
        assertTrue(Hash.check("password", hash));
        assertFalse(Hash.check("wrongPassword", hash));
    }
}