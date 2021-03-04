package app.text_quest.controller.oauth.util.enums;


public enum ReqParam {
    // For button's ref
    REDIRECT_URI, TOKEN, RESPONSE_TYPE, STATE, SCOPE,
    // For the code request
    CLIENT_ID, CLIENT_SECRET, ERROR, GRANT_TYPE, CODE,
    // For the id request
    ACCESS_TOKEN, DISPLAY, FORMAT, OAUTH_TOKEN, FIELDS, V;

    public String lowName() {
        return this.name().toLowerCase();
    }
}
