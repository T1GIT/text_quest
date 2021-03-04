package app.text_quest.controller.oauth.util.exceptions.types;

import app.text_quest.controller.oauth.util.exceptions.OauthException;


public class InvalidStateException extends OauthException {

    public InvalidStateException(String extState, String intState) {
        super(String.format("external: [%s] internal [%s]", extState, intState));
    }
}
