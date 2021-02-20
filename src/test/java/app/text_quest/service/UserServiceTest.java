package app.text_quest.service;

import app.text_quest.model.Psw;
import app.text_quest.model.User;
import app.text_quest.util.modelFactory.types.UserFactory;
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
    void addUser() {
        User user = userFactory.create();
        User findUser = userService.getByEmail(user.getEmail());
        if (findUser != null) {
            userService.delete(findUser);
        }
        userService.addUser(user);
    }

    @Test
    @Transactional
    void delete() {
        User user = userFactory.create();
        if (userService.getByEmail(user.getEmail()) == null) {
            userService.addUser(user);
        }
        userService.delete(user);
    }

    @Test
    @Transactional
    void getByEmail() {
        System.out.println(userFactory.getEmail());
    }

    @Test
    @Transactional
    void editUser() {
        if (userService.getByEmail(userFactory.getEmail()) == null) {
            userService.addUser(userFactory.create());
        }
        User user = userService.getByEmail(userFactory.getEmail());
        user.setName("name");
        Psw psw = user.getPsw();
        psw.setSalt("newPswSalt");
        userService.editUser(user);
    }

    @Test
    void getAll() {
        System.out.println(userService.getAll());
    }
}