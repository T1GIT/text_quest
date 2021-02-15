package app.text_quest.service.impl;

import app.text_quest.model.node.Node;
import app.text_quest.repo.NodeRepository;
import app.text_quest.service.NodeService;
import app.text_quest.service.NodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class NodeServiceImpl implements NodeService {
    private final NodeRepository nodeRepository;

    public NodeServiceImpl(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public Node addNode(Node node) {
        return nodeRepository.saveAndFlush(node);
    }

    @Override
    public void delete(Node node) {
        nodeRepository.delete(node);
    }

    @Override
    public Node editNode(Node node) {
        return nodeRepository.saveAndFlush(node);
    }

    @Override
    public List<Node> getAll() {
        return nodeRepository.findAll();
    }
}
