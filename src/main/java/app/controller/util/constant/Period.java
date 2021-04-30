package app.controller.util.constant;

import app.util.AbstractConstant;


/**
 * Constants class containing periods of time measuring in seconds
 */
public final class Period extends AbstractConstant {

    public final static int SECOND = 1;
    public final static int MINUTE = 60 * SECOND;
    public final static int HOUR = 60 * MINUTE;
    public final static int DAY = 24 * HOUR;
    public final static int YEAR = 365 * DAY;

    /**
     * Gets amount of milliseconds from seconds
     * @param sec amount of seconds
     * @return amount of milliseconds
     */
    public static int getMillis(int sec) {
        return (int) (sec * 1e3);
    }

    /**
     * Translates seconds into nanoseconds
     * @param sec amount of seconds
     * @return amount of nanoseconds
     */
    public static int getNanos(int sec) {
        return (int) (sec * 1e9);
    }
}
