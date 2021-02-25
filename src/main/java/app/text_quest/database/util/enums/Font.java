package app.text_quest.database.util.enums;

import app.text_quest.controller.util.oauth.enums.OauthProvider;
import app.text_quest.util.EnumNameGettable;


public enum Font implements EnumNameGettable {
    TIMES_NEW_ROMAN, ARIAL;

    @Override
    public String getName() {
        return OauthProvider.values()[this.ordinal()].toString().toLowerCase();
    }
}
