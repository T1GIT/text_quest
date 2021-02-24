package app.text_quest.database.model;

import app.text_quest.database.service.UserService;
import app.text_quest.database.util.modelFactory.types.UserFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@EnableAutoConfiguration
@Configuration
class UserTest {

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    void getPsw() {
        User user = new UserFactory().create();
        userService.addUser(user);
        System.out.println(user.getPsw());
        userService.delete(user);
    }
}