package app.text_quest.security.util.secretFactory.types;

import app.text_quest.security.util.secretFactory.AbstractSecretFactory;


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
        super(512);
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
