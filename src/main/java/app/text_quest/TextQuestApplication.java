package app.text_quest;

import app.text_quest.util.FileLogger;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
public class TextQuestApplication {

    private static final Logger logger = Logger.getLogger("rootLogger");

    public static void main(String[] args) {
        try {
            SpringApplication.run(TextQuestApplication.class, args);
        } catch (Exception e) {
            logger.fatal(FileLogger.getExceptionLog(e));
        }
    }
}
