package app.controller.util.constant;

import app.util.AbstractConstant;


/**
 * Constants class containing periods of time measuring in seconds
 */
public final class Period extends AbstractConstant {

    public final static long SECOND = 1;
    public final static long MINUTE = 60 * SECOND;
    public final static long HOUR = 60 * MINUTE;
    public final static long DAY = 24 * HOUR;
    public final static long YEAR = 365 * DAY;
    public final static long DECADE = 10 * YEAR;
    public final static long CENTURY = 100 * YEAR;
    public final static long MILLENNIUM = 1000 * YEAR;


    /**
     * Gets amount of milliseconds from seconds
     * @param sec amount of seconds
     * @return amount of milliseconds
     */
    public static long getMillis(long sec) {
        return (long) (sec * 1e3);
    }

    /**
     * Translates seconds into nanoseconds
     * @param sec amount of seconds
     * @return amount of nanoseconds
     */
    public static long getNanos(long sec) {
        return (long) (sec * 1e9);
    }
}
