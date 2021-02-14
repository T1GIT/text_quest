package app.text_quest.util.modelFactory.types.node.types.LinkedNode;

import app.text_quest.model.node.Node;
import app.text_quest.model.node.types.LinkedNode.LinkedNode;
import app.text_quest.util.modelFactory.AbstractModelFactory;
import app.text_quest.util.modelFactory.types.node.NodeFactory;

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
