package app.text_quest;

import app.text_quest.repo.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing

//@EnableAutoConfiguration
//@Configuration
public class TextQuestApplication {

    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(TextQuestApplication.class);
//        UserRepository userRepository = context.getBean(UserRepository.class);
//
//        System.out.println(userRepository.findAll());
        SpringApplication.run(TextQuestApplication.class, args);
    }

}
