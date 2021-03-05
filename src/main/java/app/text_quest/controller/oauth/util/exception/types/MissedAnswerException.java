package app.text_quest.controller.oauth.util.exception.types;

import app.text_quest.controller.oauth.util.exception.OauthException;


public class MissedAnswerException extends OauthException {

    public MissedAnswerException(String msg) {
        super(msg);
    }
}
