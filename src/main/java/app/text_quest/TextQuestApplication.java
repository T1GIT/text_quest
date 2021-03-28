package app.text_quest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 <h2> A class {@link TextQuestApplication}

 <p> An entry class for running Spring server.

 <p> Allows getting root url using {@link TextQuestApplication#getRootUrl()}

 @author Derbin Dmitriy
 @version 0.8 */
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
public class TextQuestApplication {

    private static final String rootUrl = "http://localhost:8080"; // PROD: Change

    /**
     Allows getting root url.

     @return url for the running host address
     */
    public static String getRootUrl() {
        return rootUrl;
    }

    /**
     Starts the app.

     @param args arguments from runner
     */
    public static void main(String[] args) {
        SpringApplication.run(TextQuestApplication.class, args);
    }
}
