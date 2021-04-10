package app.controller.oauth.util.constant;


import app.util.AbstractConstant;

/**
 * Constant class containing properties names that held in the properties files
 */
public final class PropName extends AbstractConstant {

    /**
     * String received when register your app on the service.
     * Identifies your app.
     */
    public final static String CLIENT_ID = "client_id";

    /**
     * String received when register your app on the service.
     * Accepts your requests on the oauth server.
     */
    public final static String CLIENT_SECRET = "client_secret";

    /**
     * Domain used to redirect user to commit a side authorisation.
     */
    public final static String DOMAIN_AUTH = "domain_auth";

    /**
     * Domain used to get access token from the oauth service.
     */
    public final static String DOMAIN_TOKEN = "domain_token";

    /**
     * Domain used to get unique user id, used to identify user through the service.
     */
    public final static String DOMAIN_ID = "domain_id";

    /**
     * Set of information requested from the oauth service about the user.
     */
    public final static String SCOPE = "scope";

}
