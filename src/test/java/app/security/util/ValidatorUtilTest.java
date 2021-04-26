package app.security.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ValidatorUtilTest {

    @Test
    void mail() {
        assertTrue(ValidatorUtil.mail("somemail@mail.ru"));
        assertFalse(ValidatorUtil.mail("ssefefmaeefeffefffefil#aefamail.ru"));
    }

    @Test
    void psw() {
        assertFalse(ValidatorUtil.psw("34dfd"));
        assertFalse(ValidatorUtil.psw("12323444324424"));
        assertFalse(ValidatorUtil.psw("efjifjfjaefef"));
        assertTrue(ValidatorUtil.psw("efjifjfef34343423553453454jaefef"));
        assertTrue(ValidatorUtil.psw("efjifjfef3###@43434234jaefef"));
    }
}