package app.util.constant;


import app.util.AbstractConstant;


/**
 * Constant class, containing standard names for log files
 */
public final class LogType extends AbstractConstant {

    /**
     * Records that are not suitable for other loggers
     */
    public final static String ROOT = "root";

    /**
     * Suitable for recording exceptions
     */
    public final static String ERROR = "error";

    /**
     * Info about incoming requests and their answers
     */
    public final static String REQUEST = "request";

    /**
     * Info about interacting with oauth servers:
     * requests and received data
     */
    public final static String OAUTH = "oauth";

    /**
     * Info about procedure of email authorising
     */
    public final static String EMAIL = "email";

    /**
     * Info about accessing of administrators to their rights
     */
    public final static String ADMIN = "admin";

}
