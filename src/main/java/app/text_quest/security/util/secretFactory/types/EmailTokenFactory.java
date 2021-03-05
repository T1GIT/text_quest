package app.text_quest.security.util.secretFactory.types;

import app.text_quest.security.util.secretFactory.AbstractSecretFactory;


public class EmailTokenFactory extends AbstractSecretFactory<String> {

    public EmailTokenFactory() {
        super(30);
    }

    @Override
    public String create() {
        return rndString(length);
    }
}
