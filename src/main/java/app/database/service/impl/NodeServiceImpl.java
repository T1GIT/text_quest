package app.database.service.impl;

import app.database.model.node.Node;
import app.database.repo.NodeRepository;
import app.database.service.NodeService;
import app.database.util.abstractService.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @see app.database.service.NodeService
 */
@Service
@Transactional
public class NodeServiceImpl
        extends AbstractService<Node, NodeRepository>
        implements NodeService {

    public NodeServiceImpl(NodeRepository repository) {
        super(repository);
    }

    @Override
    public Node getFirst() {
        return repository.findFirstByOrderById();
    }
}
