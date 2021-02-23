package app.text_quest.util;

import app.text_quest.util.enums.LogType;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.IOException;
import java.util.HashMap;


public class LoggerFactory {

    private static final HashMap<String, Logger> loggers = new HashMap<String, Logger>();
    private static final Logger errorLogger = LoggerFactory.getLogger(LogType.ERROR);


    public static Logger getLogger(LogType logType) {
        return getLogger(logType.getName().toLowerCase());
    }

    public static Logger getLogger(String name) {
        if (loggers.containsKey(name)) {
            return loggers.get(name);
        } else {
            Logger logger = Logger.getLogger(name);
            FileAppender appender;
            try {
                PatternLayout layout = new PatternLayout();
                layout.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p - %m%n");
                String fileName = ".log\\" + name + ".log";
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
