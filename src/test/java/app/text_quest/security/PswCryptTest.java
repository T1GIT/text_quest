package app.text_quest.security;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PswCryptTest {

    @Test
    void getHashOf() {
        System.out.println(Arrays.toString(PswCrypt.getHashOf("password", PswCrypt.genSalt())));
    }

    @Test
    void genSalt() {
        System.out.println(Arrays.toString(PswCrypt.genSalt()));
    }

    @Test
    void checkPassword() {
        byte[] salt = PswCrypt.genSalt();
        byte[] hash = PswCrypt.getHashOf("password", salt);
        assertTrue(PswCrypt.checkPassword("password", salt, hash));
        assertFalse(PswCrypt.checkPassword("wrong_password", salt, hash));
    }
}