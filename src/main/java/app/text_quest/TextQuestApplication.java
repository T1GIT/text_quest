package app.text_quest;

import app.text_quest.util.LoggerFactory;
import app.text_quest.util.enums.LogType;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
public class TextQuestApplication {

    private static final Logger errorLogger = LoggerFactory.getLogger(LogType.ERROR);
    private static final String rootUrl = "http://localhost:8080"; // PROD: Change


    public static String getRootUrl() {
        return rootUrl;
    }

    public static void main(String[] args) {
        try {
            SpringApplication.run(TextQuestApplication.class, args);
        } catch (Exception e) {
            errorLogger.fatal(e.getMessage(), e);
        }
    }
}
