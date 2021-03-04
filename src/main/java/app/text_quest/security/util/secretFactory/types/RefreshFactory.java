package app.text_quest.security.util.secretFactory.types;

import app.text_quest.security.util.secretFactory.AbstractSecretFactory;


public class RefreshFactory extends AbstractSecretFactory<String> {

    public RefreshFactory() {
        super(50);
    }

    @Override
    public String create() {
        return rndString(length);
    }
}
