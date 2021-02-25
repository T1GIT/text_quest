package app.text_quest.database.util.enums;

import app.text_quest.controller.util.oauth.enums.OauthProvider;
import app.text_quest.util.EnumNameGettable;


public enum Color implements EnumNameGettable {
    RED(""),
    BLUE(""),
    GREEN(""),
    YELLOW(""),
    PURPLE("");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getName() {
        return OauthProvider.values()[this.ordinal()].toString().toLowerCase();
    }

    @Override
    public String toString() {
        return "Color{" +
                "value='" + value + '\'' +
                '}';
    }
}
