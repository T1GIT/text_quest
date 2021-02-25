package app.text_quest.controller.util.oauth.enums;

import app.text_quest.util.EnumNameGettable;


public enum OauthProvider implements EnumNameGettable {
    YANDEX, GOOGLE, VK, GIT, MAIL;

    @Override
    public String getName() {
        return OauthProvider.values()[this.ordinal()].toString().toLowerCase();
    }
}
