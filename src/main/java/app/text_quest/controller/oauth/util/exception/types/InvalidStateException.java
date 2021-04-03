package app.text_quest.controller.oauth.util.exception.types;

import app.text_quest.controller.oauth.util.exception.OauthException;


/**
 * An exception throwing if state cookie and state in the oauth server request doesn't match.
 */
public class InvalidStateException extends OauthException {

    public InvalidStateException(String extState, String intState) {
        super(String.format("external: [%s] internal [%s]", extState, intState));
    }
}
