package app.security.secretFactory.types;

import app.security.util.constant.SecretLength;
import app.security.secretFactory.AbstractSecretFactory;


/**
 * Parses salt suitable for adding to hash.
 *
 * @see AbstractSecretFactory
 */
public class SaltFactory extends AbstractSecretFactory<byte[]> {

    /**
     * Creates factory for parsing salt
     */
    public SaltFactory() {
        super(SecretLength.SALT);
    }

    /**
     * Creates a salt
     *
     * @return salt bytes array
     */
    @Override
    public byte[] create() {
        return rndBytes();
    }
}
