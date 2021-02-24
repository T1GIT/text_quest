package app.text_quest.security;

import org.apache.log4j.Logger;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;


public class PswCrypt {

    private static final Logger logger = Logger.getLogger("errorLogger");
    private static final Random RANDOM = new SecureRandom();
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 512;

    private static byte[] hashBytes(char[] password, byte[] salt) {
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
            SecretKey key = secretKeyFactory.generateSecret(spec);
            return key.getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error("PasswordCrypt", e);
        }
        return null;
    }

    public static byte[] getHashOf(String password, byte[] salt) {
        return hashBytes(password.toCharArray(), salt);
    }

    public static byte[] genSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    public static boolean checkPassword(String password, byte[] salt, byte[] hash) {
        return Arrays.equals(getHashOf(password, salt), hash);
    }
}
