package app.text_quest.security.util.secretFactory.types;

import app.text_quest.security.util.secretFactory.AbstractSecretFactory;


public class JwtKeyFactory extends AbstractSecretFactory<byte[]> {

    public JwtKeyFactory() {
        super(100);
    }

    @Override
    public byte[] create() {
        return rndBytes(length);
    }
}
