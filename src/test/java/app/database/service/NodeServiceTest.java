package app.database.service;

import app.database.model.node.Node;
import app.database.model.node.types.Fork;
import app.database.model.node.types.LinkedNode.LinkedNode;
import app.database.util.modelFactory.types.BranchFactory;
import app.database.util.modelFactory.types.node.NodeFactory;
import app.database.util.modelFactory.types.node.types.ForkFactory;
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

    private final static NodeFactory nodeFactory = new NodeFactory();
    private final static ForkFactory forkFactory = new ForkFactory();
    private final static BranchFactory branchFactory = new BranchFactory();
    @Resource
    @Autowired
    private NodeService nodeService;

    @Test
    void add() {
        Node node = nodeFactory.create();
        try {
            nodeService.add(node);
        } finally {
            nodeService.delete(node);
        }
    }

    @Test
    void delete() {
        Node node = nodeFactory.create();
        nodeService.add(node);
        nodeService.delete(node);
    }

    @Transactional
    @Test
    void update() {
        Fork fork = forkFactory.create();
        try {
            nodeService.add(fork);
            fork.addBranch(branchFactory.create());
            nodeService.add(fork);
        } finally {
            nodeService.delete(fork);
        }
    }

    @Test
    void getAll() {
        System.out.println(nodeService.getAll());
    }

    @Test
    void checkTypeDefinition() {
        LinkedNode linkedNode = new LinkedNode();
        try {
            Node fork = new Fork();
            linkedNode.setNextNode(fork);
            nodeService.add(linkedNode);
            Node node = linkedNode.getNextNode();
            assert Fork.class == node.getClass();
        } finally {
            nodeService.delete(linkedNode);
        }
    }
}