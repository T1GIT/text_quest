package app.text_quest.util.constant;


import app.text_quest.util.AbstractConstant;


/**
 <h2> A constants class {@link LogType}

 <p> Contains standard name for log files
 */
public final class LogType extends AbstractConstant {

    /**
     Records that are not suitable for other loggers
     */
    public final static String ROOT = "root";

    /**
     Suitable for recording exceptions
     */
    public final static String ERROR = "error";

    /**
     Info about incoming requests and their answers
     */
    public final static String REQUEST = "request";

    /**
     Info about interacting with oauth servers:
     requests and received data
     */
    public final static String OAUTH = "oauth";

}
