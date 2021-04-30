package app.security.util.constants;

import app.util.AbstractConstant;


/**
 * Constants class containing lengths of the secret keys,
 * created by the {@link app.security.secretFactory.AbstractSecretFactory} children
 */
public class SecretLength extends AbstractConstant {

    /**
     * Length of the email token
     */
    public final static int EMAIL_TOKEN = 100;

    /**
     * Length of the secret JWT signature
     */
    public final static int JWT_KEY = 50;

    /**
     * Length of the refresh token
     */
    public final static int REFRESH = 100;

    /**
     * Length of the salt used when hashing
     */
    public final static int SALT = 512;

    /**
     * Length of the state, used for defencing from the CSRF-attacks
     */
    public final static int STATE = 30;

    /**
     * Length of the socket identifier
     */
    public final static int SOCKET_ID = 100;
}
