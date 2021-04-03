package app.text_quest.controller.oauth.util.exception.types;

import app.text_quest.controller.oauth.util.exception.OauthException;


/**
 * An exception throwing if state cookie doesn't exist.
 */
public class MissedStateCookieException extends OauthException {

    public MissedStateCookieException() {
        super("Can't find cookie \"state\"");
    }
}
