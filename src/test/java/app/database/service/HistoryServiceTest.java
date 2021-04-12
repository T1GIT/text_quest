package app.database.service;

import app.database.model.History;
import app.database.model.node.Node;
import app.database.model.user.User;
import app.database.service.userService.UserService;
import app.database.util.modelFactory.types.HistoryFactory;
import app.database.util.modelFactory.types.UserFactory;
import app.database.util.modelFactory.types.node.NodeFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableAutoConfiguration
@Configuration
class HistoryServiceTest {

    private final static HistoryFactory historyFactory = new HistoryFactory();
    private final static UserFactory userFactory = new UserFactory();
    private final static NodeFactory nodeFactory = new NodeFactory();
    @Autowired
    private NodeService nodeService;
    @Autowired
    private UserService userService;
    @Autowired
    private HistoryService historyService;

    @Test
    @Transactional
    void getLastNodes() {
        User user = userFactory.create();
        userService.add(user);
        for (int i = 0; i < 20; i++) {
            History history = new History();
            Node node = nodeFactory.create();
            nodeService.add(node);
            history.setNode(node);
            history.setUser(user);
            historyService.add(history);
        }
        historyService.getLastNodes(user).forEach(userItem -> {
            System.out.println(userItem.getId());
        });
    }
}