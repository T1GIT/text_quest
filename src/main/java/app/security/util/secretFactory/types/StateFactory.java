package app.security.util.secretFactory.types;

import app.security.util.constants.SecretLength;
import app.security.util.secretFactory.AbstractSecretFactory;


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
        super(SecretLength.STATE);
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
