package app.security.secretFactory.types;

import app.security.util.constants.SecretLength;
import app.security.secretFactory.AbstractSecretFactory;


/**
 * Parses socket id suitable for subscribing as a part of the url.
 *
 * @see AbstractSecretFactory
 */
public class SocketIdFactory extends AbstractSecretFactory<String> {

    /**
     * Creates factory for parsing email tokens
     */
    public SocketIdFactory() {
        super(SecretLength.SOCKET_ID);
    }

    /**
     * Creates a socket id
     *
     * @return id string
     */
    @Override
    public String create() {
        return rndString();
    }
}
