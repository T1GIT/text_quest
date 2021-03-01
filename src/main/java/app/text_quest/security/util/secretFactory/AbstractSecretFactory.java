package app.text_quest.security.util.secretFactory;

import app.text_quest.security.Crypt;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public abstract class AbstractSecretFactory<T> {

    private static final Logger logger = Logger.getLogger("errorLogger");

    public static String rndString(int length) {
        return Crypt.toHex(rndBytes(length));
    }

    protected static byte[] rndBytes(int length) {
        byte[] bytes = new byte[length];
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.nextBytes(bytes);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(), e);
        }
        return bytes;
    }

    public abstract T create();
}
