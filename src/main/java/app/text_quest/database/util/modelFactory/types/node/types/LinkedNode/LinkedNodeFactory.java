package app.text_quest.database.util.modelFactory.types.node.types.LinkedNode;

import app.text_quest.database.model.node.Node;
import app.text_quest.database.model.node.types.LinkedNode.LinkedNode;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;
import app.text_quest.database.util.modelFactory.types.node.NodeFactory;


/**
 @see app.text_quest.database.util.modelFactory.AbstractModelFactory */
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
