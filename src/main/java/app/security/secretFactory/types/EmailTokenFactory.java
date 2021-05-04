package app.security.secretFactory.types;

import app.security.util.constant.SecretLength;
import app.security.secretFactory.AbstractSecretFactory;


/**
 * Parses email token suitable for identifying user in its email.
 *
 * @see AbstractSecretFactory
 */
public class EmailTokenFactory extends AbstractSecretFactory<String> {

    /**
     * Creates factory for parsing email tokens
     */
    public EmailTokenFactory() {
        super(SecretLength.EMAIL_TOKEN);
    }

    /**
     * Creates an email token
     *
     * @return token string
     */
    @Override
    public String create() {
        return rndString();
    }
}
