package app.text_quest.controller.util.constant;

import app.text_quest.util.AbstractConstant;


/**
 * Constants class containing periods of time measuring in seconds
 */
public final class Period extends AbstractConstant {

    public final static int SECOND = 1;
    public final static int MINUTE = 60 * SECOND;
    public final static int HOUR = 60 * MINUTE;
    public final static int DAY = 24 * HOUR;
    public final static int YEAR = 365 * DAY;

}
