package app.controller.oauth.util.exception.types;

import app.controller.oauth.util.exception.OauthException;


/**
 * An exception throwing if oauth server responded with an error
 */
public class ApiException extends OauthException {

    private final int code;

    public ApiException(String msg, int code) {
        super(code + " : " + msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
