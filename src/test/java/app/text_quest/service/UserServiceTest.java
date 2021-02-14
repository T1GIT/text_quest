package app.text_quest.service;

import app.text_quest.model.User;
import app.text_quest.util.modelCreator.HistoryCreator;
import app.text_quest.util.modelCreator.UserCreator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@EnableAutoConfiguration
@Configuration
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void addUser() {
        userService.addUser(UserCreator.create());
    }

    @Test
    @Transactional
    void getByEmail() {
        System.out.println(userService.getByEmail(""));
    }

    @Test
    void editUser() {
        User user =  userService.getByEmail(UserCreator.create().getEmail());
        user.setName("name");
        userService.editUser(user);

    }

    @Test
    void getAll() {
        System.out.println(userService.getAll());
    }

    @Test
    @Transactional
    void delete() {
        User user = UserCreator.create();
        userService.addUser(user);
        userService.delete(user);
    }
}