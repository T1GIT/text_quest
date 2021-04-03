package app.text_quest.controller.oauth.util.constant;


import app.text_quest.util.AbstractConstant;


/**
 * A constant class for using as a parameter name in requests.
 */
public final class ReqParam extends AbstractConstant {

    // For button's ref
    public final static String REDIRECT_URI = "redirect_uri";
    public final static String TOKEN = "token";
    public final static String RESPONSE_TYPE = "response_type";
    public final static String STATE = "state";
    public final static String SCOPE = "scope";
    // For the code request
    public final static String CLIENT_ID = "client_id";
    public final static String CLIENT_SECRET = "client_secret";
    public final static String GRANT_TYPE = "grant_type";
    public final static String CODE = "code";
    // For the id request
    public final static String ACCESS_TOKEN = "access_token";
    public final static String OAUTH_TOKEN = "oauth_token";
    public final static String DISPLAY = "display";
    public final static String FORMAT = "format";
    public final static String FIELDS = "fields";
    public final static String V = "v";
    // For errors
    public final static String ERROR = "error";
    public final static String ERROR_DESCRIPTION = "error_description";

}
