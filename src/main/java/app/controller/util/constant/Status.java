package app.controller.util.constant;

import app.util.AbstractConstant;


/**
 * Constants class containing error messages for a rejected
 * authorisation attempts.
 */
public final class Status extends AbstractConstant {

    /**
     * If user was not found when login
     */
    public final static String NOT_FOUND = "not_found";

    /**
     * If user gabe incorrect password when login
     */
    public final static String INVALID_PSW = "invalid_psw";

    /**
     * If user with given creditionals already exists in the database when register
     */
    public final static String EXISTS = "exists";

    /**
     * If user gave invalid email when register
     */
    public final static String BAD_EMAIL = "bad_email";

    /**
     * If user gave invalid password when register
     */
    public final static String BAD_PSW = "bad_psw";

}
