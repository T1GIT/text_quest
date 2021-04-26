package app.security.secretFactory.types;

import app.security.util.constants.SecretLength;
import app.security.secretFactory.AbstractSecretFactory;


/**
 * Parses JWT key suitable encrypting JWT.
 *
 * @see AbstractSecretFactory
 */
public class JwtKeyFactory extends AbstractSecretFactory<byte[]> {

    /**
     * Creates factory for parsing JWT key
     */
    public JwtKeyFactory() {
        super(SecretLength.JWT_KEY);
    }

    /**
     * Creates a JWT key
     *
     * @return key bytes array
     */
    @Override
    public byte[] create() {
        return rndBytes();
    }
}
