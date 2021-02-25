package app.text_quest.controller.util.oauth.enums;

import app.text_quest.util.EnumNameGettable;


public enum OauthReqParam implements EnumNameGettable {
    CODE, REDIRECT_URI, RESPONSE_TYPE, CLIENT_ID, ERROR, TOKEN,
    CLIENT_SECRET, METHOD, GRANT_TYPE, ACCESS_TOKEN, DISPLAY;

    @Override
    public String getName() {
        return OauthReqParam.values()[this.ordinal()].toString().toLowerCase();
    }
}
