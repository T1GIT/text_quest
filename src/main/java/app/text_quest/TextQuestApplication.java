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

    public static void main(String[] args) {
        System.out.println("https://oauth.yandex.ru/authorize?client_id=fe4effc1e75340d8a1a93f5acfb09827&redirect_uri=localhost:8080/oauth/yandex/code&response_type=token&display=popup".length());
        try {
            SpringApplication.run(TextQuestApplication.class, args);
        } catch (Exception e) {
            errorLogger.fatal(e.getMessage(), e);
        }
    }
}
