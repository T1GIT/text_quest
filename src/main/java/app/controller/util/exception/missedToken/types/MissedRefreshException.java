package app.controller.util.exception.missedToken.types;

import app.controller.util.exception.missedToken.MissedTokenException;


/**
 * Exception throwing if refresh token was missed
 * in the cookie or in the database
 */
public class MissedRefreshException extends MissedTokenException {

}
