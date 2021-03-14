package app.text_quest.security.util.secretFactory;

import app.text_quest.security.util.HexConvertor;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public abstract class AbstractSecretFactory<T> {

    private static final Logger logger = Logger.getLogger("errorLogger");

    protected final int length;

    protected AbstractSecretFactory(int length) {
        this.length = length;
    }

    protected static String rndString(int length) {
        return HexConvertor.toHex(rndBytes(length));
    }

    public int getLength() {
        return length;
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
