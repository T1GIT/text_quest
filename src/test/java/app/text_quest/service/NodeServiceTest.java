package app.text_quest.service;

import app.text_quest.model.node.Node;
import app.text_quest.model.node.types.Fork;
import app.text_quest.util.modelFactory.types.BranchFactory;
import app.text_quest.util.modelFactory.types.node.NodeFactory;
import app.text_quest.util.modelFactory.types.node.types.ForkFactory;
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
class NodeServiceTest {

    @Resource
    @Autowired
    private NodeService nodeService;
    private final static NodeFactory nodeFactory = new NodeFactory();
    private final static ForkFactory forkFactory = new ForkFactory();
    private final static BranchFactory branchFactory = new BranchFactory();

    @Test
    void addNode() {
        nodeService.addNode(nodeFactory.create());
    }

    @Test
    @Transactional
    void delete() {
        Node node = nodeFactory.create();
        nodeService.addNode(node);
        nodeService.delete(node);
    }

    @Transactional
    @Test
    void editNode() {
        Fork fork = forkFactory.create();
        nodeService.addNode(fork);
        fork.addBranch(branchFactory.create());
        nodeService.editNode(fork);
    }

    @Transactional
    @Test
    void getAll() {
        System.out.println(nodeService.getAll());
    }
}