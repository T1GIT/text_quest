package app.text_quest.security.util.secretFactory.types;

import app.text_quest.security.util.secretFactory.AbstractSecretFactory;


public class SaltFactory extends AbstractSecretFactory<byte[]> {

    private static final int LENGTH = 16;

    @Override
    public byte[] create() {
        return rndBytes(LENGTH);
    }
}
