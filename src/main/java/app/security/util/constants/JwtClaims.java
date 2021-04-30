package app.security.util.constants;

import app.util.AbstractConstant;


/**
 * Constants class containing user's attributes, saved in a JWT
 */
public class JwtClaims extends AbstractConstant {

    /**
     * User's id in the database
     */
    public final static String ID = "id";

    /**
     * User's name
     */
    public final static String NAME = "name";

    /**
     * User's current socket id
     */
    public final static String SOCKET_ID = "socketId";

}
