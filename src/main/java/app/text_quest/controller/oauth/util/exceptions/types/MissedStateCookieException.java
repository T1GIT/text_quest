package app.text_quest.controller.oauth.util.exceptions.types;

import app.text_quest.controller.oauth.util.exceptions.OauthException;


public class MissedStateCookieException extends OauthException {

    public MissedStateCookieException(String msg) {
        super(msg);
    }
}
