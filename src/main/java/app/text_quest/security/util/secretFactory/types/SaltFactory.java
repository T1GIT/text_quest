package app.text_quest.security.util.secretFactory.types;

import app.text_quest.security.util.secretFactory.AbstractSecretFactory;


public class SaltFactory extends AbstractSecretFactory<byte[]> {

    public SaltFactory() {
        super(512);
    }

    @Override
    public byte[] create() {
        return rndBytes(length);
    }
}
