package app.database.service;

import app.database.model.State;
import app.database.model.user.User;
import app.database.service.userService.UserService;
import app.database.util.modelFactory.types.StateFactory;
import app.database.util.modelFactory.types.UserFactory;
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
    private final static StateFactory stateFactory = new StateFactory();
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
            userService.update(user);
            user.addState(stateFactory.create());
            userService.update(user);
            user.getStates().get(0).setVal(30);
            userService.update(user);
            System.out.println(userService.getById(user.getId()).getStates().get(0));
        } finally {
            userService.delete(user);
        }
    }

    @Test
    void getAll() {
        System.out.println(userService.getAll());
    }
}