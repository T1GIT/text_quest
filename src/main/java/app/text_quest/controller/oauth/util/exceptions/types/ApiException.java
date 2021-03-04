package app.text_quest.controller.oauth.util.exceptions.types;

import app.text_quest.controller.oauth.util.exceptions.OauthException;


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
