package app.text_quest.service;

import app.text_quest.TextQuestApplication;
import app.text_quest.model.User;
import app.text_quest.service.impl.UserServiceImpl;
import app.text_quest.util.modelCreator.UserCreator;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;


@EnableAutoConfiguration
@Configuration
class UserServiceTest {

    @Resource
    private UserService userService;

    @Before
    public void setUp() {
        ConfigurableApplicationContext context = SpringApplication.run(TextQuestApplication.class);
        this.userService = context.getBean(UserService.class);
        System.out.println("----------------------------------------------------------------------------------");
    }

    @Test
    void addUser() {
        setUp();
        userService.addUser(UserCreator.create());
    }

    @Test
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
    void delete() {
        userService.delete(userService.getByEmail(UserCreator.create().getEmail()));
    }
}