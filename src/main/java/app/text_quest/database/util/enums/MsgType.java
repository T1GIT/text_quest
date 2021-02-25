package app.text_quest.database.util.enums;

import app.text_quest.controller.util.oauth.enums.OauthProvider;
import app.text_quest.util.EnumNameGettable;


public enum MsgType implements EnumNameGettable {
    SQUARE, ROUNDED, ANGLE, TALE;

    @Override
    public String getName() {
        return OauthProvider.values()[this.ordinal()].toString().toLowerCase();
    }
}
