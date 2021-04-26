package app.security.secretFactory.types;

import app.security.util.constants.SecretLength;
import app.security.secretFactory.AbstractSecretFactory;


/**
 * Parses refresh token suitable refreshing user's access token.
 *
 * @see AbstractSecretFactory
 */
public class RefreshFactory extends AbstractSecretFactory<String> {

    /**
     * Creates factory for parsing refresh tokens
     */
    public RefreshFactory() {
        super(SecretLength.REFRESH);
    }

    /**
     * Creates an refresh token
     *
     * @return token string
     */
    @Override
    public String create() {
        return rndString();
    }
}
