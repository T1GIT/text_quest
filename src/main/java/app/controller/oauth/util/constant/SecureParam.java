package app.controller.oauth.util.constant;


import app.util.AbstractConstant;

/**
 * Constants class for names of parameters participating in the security system
 */
public final class SecureParam extends AbstractConstant {

    /**
     * Client sends state when authorising and oath server sends the same state in the request to the endpoint.
     * Both of states must be similar. Intended for defencing from the CSRF-attacks.
     */
    public final static String STATE = "state";

    /**
     * Token intended for refreshing JWT. In this process it is updated.
     */
    public final static String REFRESH = "refresh";

    /**
     * Token, containing user. It is protected by signature.
     */
    public final static String JWT = "jwt";

    /**
     * An unique user identificator received from the oauth service.
     */
    public final static String OAUTH_ID = "oauth_id";

}
