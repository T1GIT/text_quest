package app.text_quest.util.exceptions;

public class OauthApiException extends Exception {

    private final int code;

    public OauthApiException(String msg, int code) {
        super(code + " : " + msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
