package app.database.util.modelFactory.types.node;

import app.database.model.node.Node;
import app.database.util.modelFactory.AbstractModelFactory;


/**
 * @see app.database.util.modelFactory.AbstractModelFactory
 */
public class NodeFactory extends AbstractModelFactory<Node> {
    public Node create() {
        return new Node();
    }
}
