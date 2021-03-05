package app.text_quest.security;

import app.text_quest.security.util.HexConvertor;
import app.text_quest.security.util.secretFactory.types.SaltFactory;
import org.apache.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public abstract class Hash {

    private static final Logger logger = Logger.getLogger("errorLogger");
    private static final SaltFactory saltFactory = new SaltFactory();
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 512;
    private static final int SALT_LENGTH = 16;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    public static String crypt(String password) {
        char[] chars = password.toCharArray();
        byte[] salt = saltFactory.create();
        byte[] hash = null; // TODO: 06.03.2021 Add RSA crypting to form
        try {
            PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
            hash = skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error(e.getMessage(), e);
        }
        return String.format("%s:%d:%s:%s", ALGORITHM, ITERATIONS, HexConvertor.toHex(salt), HexConvertor.toHex(hash));
    }

    public static boolean check(String psw, String pswHash) {
        try {
            String[] parts = pswHash.split(":");
            String algorithm = parts[0];
            int iterations = Integer.parseInt(parts[1]);
            byte[] salt = HexConvertor.fromHex(parts[2]);
            byte[] hash = HexConvertor.fromHex(parts[3]);

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
}
