package app.text_quest.security.util.secretFactory.types;

import app.text_quest.security.util.secretFactory.AbstractSecretFactory;


public class JwtKeyFactory extends AbstractSecretFactory<byte[]> {

    private static final int LENGTH = 100;

    @Override
    public byte[] create() {
        return rndBytes(LENGTH);
    }
}
