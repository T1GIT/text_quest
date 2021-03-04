package app.text_quest.security;

import app.text_quest.security.util.secretFactory.types.SaltFactory;
import org.apache.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public abstract class Crypt {

    private static final Logger logger = Logger.getLogger("errorLogger");
    private static final SaltFactory saltFactory = new SaltFactory();
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 512;
    private static final int SALT_LENGTH = 16;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    public static String crypt(String password) {
        char[] chars = password.toCharArray();
        byte[] salt = saltFactory.create();
        byte[] hash = null;
        try {
            PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
            hash = skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error(e.getMessage(), e);
        }
        return String.format("%s:%d:%s:%s", ALGORITHM, ITERATIONS, toHex(salt), toHex(hash));
    }

    public static boolean check(String psw, String pswHash) {
        try {
            String[] parts = pswHash.split(":");
            String algorithm = parts[0];
            int iterations = Integer.parseInt(parts[1]);
            byte[] salt = fromHex(parts[2]);
            byte[] hash = fromHex(parts[3]);

            PBEKeySpec spec = new PBEKeySpec(psw.toCharArray(), salt, iterations, hash.length * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
            byte[] testHash = skf.generateSecret(spec).getEncoded();

            int diff = hash.length ^ testHash.length;
            for (int i = 0; i < hash.length && i < testHash.length; i++) {
                diff |= hash[i] ^ testHash[i];
            }
            return diff == 0;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = array.length * 2 - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    public static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
