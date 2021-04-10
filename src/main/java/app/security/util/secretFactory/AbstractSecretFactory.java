package app.security.util.secretFactory;

import app.security.util.HexConvertor;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


/**
 * Parses secure pseudo-random string
 * <p>
 * Must be extended by the class intended for parsing
 * a specified secret key.
 * <p>
 * Child class must have a constructor doesn't requesting
 * key, length and calling {@link AbstractSecretFactory#AbstractSecretFactory(int)} with standard
 * key length for class' secret key type.
 * <p>
 * Child class must override a {@link AbstractSecretFactory#create()}.
 * This overriding should return result of {@link AbstractSecretFactory#rndString()}
 * or {@link AbstractSecretFactory#rndBytes()} depend on the required type of
 * the secret key.
 *
 * @param <T> may be a <b>bytes array</b> or a <b>String</b>. Type of the created secret key
 */
public abstract class AbstractSecretFactory<T> {

    /**
     * Logger for recording exceptions
     */
    private static final Logger errLoger = Logger.getLogger("errorLogger");

    /**
     * The name of the used algorithm
     */
    private static final String ALGORITHM = "SHA1PRNG";


    /**
     * Secure random object
     */
    private SecureRandom secureRandom;

    /**
     * Amount of symbols in the parsed secret key
     */
    protected final int length;

    /**
     * A constructor intended for using in children classes.
     * Child class must have a constructor doesn't requesting
     * key, length and calling this constructor with standard
     * key length for class' secret key type.
     *
     * @param length amount of symbols in the parsed secret key
     */
    protected AbstractSecretFactory(int length) {
        this.length = length;
        try {
            this.secureRandom = SecureRandom.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            errLoger.error(e.getMessage(), e);
        }
    }

    /**
     * Creates secure pseudo-random string.
     * Should be used in the {@link AbstractSecretFactory#create()}
     * of the specified child class.
     *
     * @return created string
     */
    protected String rndString() {
        return HexConvertor
                .toHex(rndBytes((int)
                        Math.round(
                                Math.ceil(
                                        length / 2d))))
                .substring(0, length);
    }

    /**
     * Creates secure pseudo-random bytes array.
     * Should be used in the {@link AbstractSecretFactory#create()}
     * of the specified child class.
     *
     * @param length amount of elements in the array
     * @return created bytes array
     */
    private byte[] rndBytes(int length) {
        byte[] bytes = new byte[length];
        secureRandom.nextBytes(bytes);
        return bytes;
    }

    /**
     * Creates secure pseudo-random bytes array.
     * Should be used in the {@link AbstractSecretFactory#create()}
     * of the specified child class.
     *
     * @return created bytes array
     */
    protected byte[] rndBytes() {
        return rndBytes(this.length);
    }

    /**
     * Creates secure pseudo-random key of the specified type.
     * Must be overridden with proxying to {@link AbstractSecretFactory#rndString()}
     * or {@link AbstractSecretFactory#rndBytes()}
     *
     * @return created key of the specified type
     */
    public abstract T create();
}
