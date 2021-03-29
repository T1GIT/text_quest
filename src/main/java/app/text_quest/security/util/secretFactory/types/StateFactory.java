package app.text_quest.security.util.secretFactory.types;

import app.text_quest.security.util.secretFactory.AbstractSecretFactory;


/**
 * Parses state token suitable for identifying request from the oauth server.
 *
 * @see AbstractSecretFactory
 */
public class StateFactory extends AbstractSecretFactory<String> {

    /**
     * Creates factory for parsing state tokens
     */
    public StateFactory() {
        super(30);
    }

    /**
     * Creates state token
     *
     * @return token string
     */
    @Override
    public String create() {
        return rndString();
    }
}
