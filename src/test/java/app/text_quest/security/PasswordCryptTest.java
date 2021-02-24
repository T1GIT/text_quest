package app.text_quest.security;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PasswordCryptTest {

    @Test
    void getHashOf() {
        System.out.println(Arrays.toString(PasswordCrypt.getHashOf("password", PasswordCrypt.genSalt())));
    }

    @Test
    void genSalt() {
        System.out.println(Arrays.toString(PasswordCrypt.genSalt()));
    }

    @Test
    void checkPassword() {
        byte[] salt = PasswordCrypt.genSalt();
        byte[] hash = PasswordCrypt.getHashOf("password", salt);
        assertTrue(PasswordCrypt.checkPassword("password", salt, hash));
        assertFalse(PasswordCrypt.checkPassword("wrong_password", salt, hash));
    }
}