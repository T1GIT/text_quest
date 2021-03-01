package app.text_quest.security.util.secretFactory.types;

import app.text_quest.security.util.secretFactory.AbstractSecretFactory;


public class StateFactory extends AbstractSecretFactory<String> {

    private static final int LENGTH = 30;

    @Override
    public String create() {
        return rndString(LENGTH);
    }
}
