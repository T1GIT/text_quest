package app.text_quest.util.enums;

public enum LogType {
    ERROR, REQUEST, ROOT;

    public String getName() {
        return LogType.values()[this.ordinal()].toString();
    }
}
