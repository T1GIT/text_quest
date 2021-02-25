package app.text_quest.util.enums;

import app.text_quest.util.EnumNameGettable;


public enum LogType implements EnumNameGettable {
    ERROR, REQUEST, ROOT;

    @Override
    public String getName() {
        return LogType.values()[this.ordinal()].toString().toLowerCase();
    }
}
