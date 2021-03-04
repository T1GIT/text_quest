package app.text_quest.controller.util.enums;

public enum Period {
    YEAR(365 * 24 * 60 * 60),
    DAY(24 * 60 * 60),
    HOUR(60 * 60),
    MINUTE(60),
    SECOND(1);

    private final int sec;

    Period(int sec) {
        this.sec = sec;
    }

    public int getSec() {
        return sec;
    }
}
