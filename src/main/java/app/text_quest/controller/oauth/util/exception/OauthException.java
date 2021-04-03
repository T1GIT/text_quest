package app.text_quest.controller.oauth.util.exception;


/**
 * An exception throwing if some problem with oauth authorisation occurs
 */
public class OauthException extends Exception {

    /**
     * Class constructor
     */
    public OauthException() {
        super();
    }

    /**
     * Class constructor, specifying message text
     *
     * @param msg info about the error
     */
    public OauthException(String msg) {
        super(msg);
    }
}
