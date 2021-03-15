package app.text_quest.util;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.util.HashMap;


public class LoggerFactory {

    private static final HashMap<String, Logger> loggers = new HashMap<String, Logger>();

    public static Logger getLogger(String name) {
        if (loggers.containsKey(name)) {
            return loggers.get(name);
        } else {
            Logger logger = Logger.getLogger(name);
            ConsoleAppender appender;
//            FileAppender appender;
            PatternLayout layout = new PatternLayout();
            layout.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p - %m%n");
//            String fileName = ".log\\" + name + ".log";
            appender = new ConsoleAppender(layout);
//                appender = new FileAppender(layout, fileName);
            logger.addAppender(appender);
            loggers.put(name, logger);
            return logger;
        }
    }
}
