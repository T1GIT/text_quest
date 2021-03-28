package app.text_quest.controller.oauth.util.exception.types;

import app.text_quest.controller.oauth.util.exception.OauthException;


public class MissedStateCookieException extends OauthException {

    public MissedStateCookieException() {
        super("Can't find cookie \"state\"");
    }
}
