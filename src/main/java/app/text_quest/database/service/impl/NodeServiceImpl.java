package app.text_quest.database.service.impl;

import app.text_quest.database.model.node.Node;
import app.text_quest.database.repo.NodeRepository;
import app.text_quest.database.service.NodeService;
import app.text_quest.database.util.abstractService.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @see app.text_quest.database.service.NodeService
 */
@Service
@Transactional
public class NodeServiceImpl
        extends AbstractService<Node, NodeRepository>
        implements NodeService {

    public NodeServiceImpl(NodeRepository repository) {
        super(repository);
    }
}
