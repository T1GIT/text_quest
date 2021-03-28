package app.text_quest.security.util.secretFactory.types;

import app.text_quest.security.util.secretFactory.AbstractSecretFactory;


/**
 <h2> An abstract class {@link JwtKeyFactory}

 <p> Intended for parsing JWT key suitable encrypting JWT.

 @see AbstractSecretFactory */
public class JwtKeyFactory extends AbstractSecretFactory<byte[]> {

    /**
     Creates factory for parsing JWT key
     */
    public JwtKeyFactory() {
        super(100);
    }

    /**
     Creates a JWT key

     @return key bytes array
     */
    @Override
    public byte[] create() {
        return rndBytes();
    }
}
