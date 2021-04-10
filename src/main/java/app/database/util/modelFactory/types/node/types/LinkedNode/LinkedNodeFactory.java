package app.database.util.modelFactory.types.node.types.LinkedNode;

import app.database.model.node.Node;
import app.database.model.node.types.LinkedNode.LinkedNode;
import app.database.util.modelFactory.AbstractModelFactory;
import app.database.util.modelFactory.types.node.NodeFactory;


/**
 * @see app.database.util.modelFactory.AbstractModelFactory
 */
public class LinkedNodeFactory extends AbstractModelFactory<LinkedNode> {

    private final static NodeFactory nodeFactory = new NodeFactory();
    private final Node nextNode = nodeFactory.create();

    public LinkedNode create() {
        LinkedNode linkedNode = new LinkedNode();
        linkedNode.setNextNode(nodeFactory.create());
        return linkedNode;
    }

    public Node getNextNode() {
        return nextNode;
    }
}
