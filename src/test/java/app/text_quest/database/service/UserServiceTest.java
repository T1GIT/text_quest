package app.text_quest.database.service;

import app.text_quest.database.model.user.User;
import app.text_quest.database.service.userService.UserService;
import app.text_quest.database.util.modelFactory.types.UserFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@SpringBootTest
@EnableAutoConfiguration
@Configuration
class UserServiceTest {

    private final static UserFactory userFactory = new UserFactory();
    @Resource
    @Autowired
    private UserService userService;

    @Test
    void add() {
        User user = userFactory.create();
        try {
            userService.add(user);
        } finally {
            userService.delete(user);
        }
    }

    @Test
    @Transactional
    void delete() {
        User user = userFactory.create();
        userService.add(user);
        userService.delete(user);
    }

    @Test
    @Transactional
    void update() {

        User user = userFactory.create();
        try {
            userService.add(user);
            user.setName("name");
            userService.add(user);
        } finally {
            userService.delete(user);
        }
    }

    @Test
    void getAll() {
        System.out.println(userService.getAll());
    }
}