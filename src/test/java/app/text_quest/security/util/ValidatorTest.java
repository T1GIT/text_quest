package app.text_quest.security.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ValidatorTest {

    @Test
    void mail() {
        assertTrue(Validator.mail("somemail@mail.ru"));
        assertFalse(Validator.mail("ssefefmaeefeffefffefil#aefamail.ru"));
    }

    @Test
    void psw() {
        assertFalse(Validator.psw("34dfd"));
        assertFalse(Validator.psw("12323444324424"));
        assertFalse(Validator.psw("efjifjfjaefef"));
        assertTrue(Validator.psw("efjifjfef34343423553453454jaefef"));
        assertTrue(Validator.psw("efjifjfef3###@43434234jaefef"));
    }
}