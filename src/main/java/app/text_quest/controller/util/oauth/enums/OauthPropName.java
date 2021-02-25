package app.text_quest.controller.util.oauth.enums;

import app.text_quest.util.EnumNameGettable;


public enum OauthPropName implements EnumNameGettable {
    CLIENT_ID, CLIENT_SECRET;

    @Override
    public String getName() {
        return OauthPropName.values()[this.ordinal()].toString().toLowerCase();
    }
}
