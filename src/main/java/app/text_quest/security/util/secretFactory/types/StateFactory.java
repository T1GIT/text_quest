package app.text_quest.security.util.secretFactory.types;

import app.text_quest.security.util.secretFactory.AbstractSecretFactory;


/**
 <h2> An abstract class {@link EmailTokenFactory}

 <p> Intended for parsing state token suitable for identifying request from
 the oauth server.

 @see AbstractSecretFactory */
public class StateFactory extends AbstractSecretFactory<String> {

    /**
     Creates factory for parsing state tokens
     */
    public StateFactory() {
        super(30);
    }

    /**
     Creates state token

     @return token string
     */
    @Override
    public String create() {
        return rndString();
    }
}
