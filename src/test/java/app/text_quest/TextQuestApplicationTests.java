package app.text_quest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;


@SpringBootTest
@EnableAutoConfiguration
@Configuration
class TextQuestApplicationTests {


    @Test
    void main() throws Exception {
        TextQuestApplication.main(null);
    }

}
