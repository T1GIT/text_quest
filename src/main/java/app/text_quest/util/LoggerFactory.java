package app.text_quest.util;

import app.text_quest.util.constant.LogType;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.IOException;
import java.util.HashMap;


/**
 * Creates {@link Logger logger} from name.
 * <p>
 * Creates file in the directory {@link LoggerFactory#logPath}
 * with name: "[given name].log".
 * <p>
 * Holds all the loggers in the hashMap, and doesn't create a new one if you
 * try to create logger with existing name, but just returns it.
 */
public class LoggerFactory {

    /**
     * Directory for saving log files
     */
    private static final String logPath = ".log";

    /**
     * Holds loggers for fast accessing
     */
    private static final HashMap<String, Logger> loggers = new HashMap<String, Logger>();

    /**
     * Logger to output errors
     */
    private static final Logger errorLogger = LoggerFactory.getLogger(LogType.ERROR);

    /**
     * Creates logger with appender to file with name "[name].log".
     *
     * @param name file name for the logger
     * @return logger
     */
    public static Logger getLogger(String name) {
        if (loggers.containsKey(name)) {
            return loggers.get(name);
        } else {
            Logger logger = Logger.getLogger(name);
            FileAppender appender;
            try {
                PatternLayout layout = new PatternLayout();
                layout.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p - %m%n");
                String fileName = logPath + "\\" + name + ".log";
                appender = new FileAppender(layout, fileName);
                logger.addAppender(appender);
            } catch (IOException e) {
                errorLogger.error(e.getMessage(), e);
            }
            loggers.put(name, logger);
            return logger;
        }
    }
}
