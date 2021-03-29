package app.text_quest.security.util.secretFactory.types;

import app.text_quest.security.util.secretFactory.AbstractSecretFactory;


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
        super(100);
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
