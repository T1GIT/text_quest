package app.text_quest.security.util.secretFactory.types;

import app.text_quest.security.util.secretFactory.AbstractSecretFactory;


/**
 <h2> An abstract class {@link RefreshFactory}

 <p> Intended for parsing refresh token suitable refreshing user's access token

 @see AbstractSecretFactory */
public class RefreshFactory extends AbstractSecretFactory<String> {

    /**
     Creates factory for parsing refresh tokens
     */
    public RefreshFactory() {
        super(50);
    }

    /**
     Creates an refresh token

     @return token string
     */
    @Override
    public String create() {
        return rndString();
    }
}
