package app.text_quest.security.util.secretFactory.types;

import app.text_quest.security.util.secretFactory.AbstractSecretFactory;


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
        super(100);
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
