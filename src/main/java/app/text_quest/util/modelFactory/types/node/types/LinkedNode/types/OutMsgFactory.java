package app.text_quest.util.modelFactory.types.node.types.LinkedNode.types;

import app.text_quest.model.node.Node;
import app.text_quest.model.node.types.LinkedNode.types.OutMsg;
import app.text_quest.util.modelFactory.AbstractModelFactory;
import app.text_quest.util.modelFactory.types.node.NodeFactory;

public class OutMsgFactory extends AbstractModelFactory<OutMsg> {
    private final static NodeFactory nodeFactory = new NodeFactory();
    private final Node node = nodeFactory.create();

    public OutMsg create() {
        OutMsg outMsg = new OutMsg();
        outMsg.setNextNode(nodeFactory.create());
        return outMsg;
    }

    public Node getNode() {
        return node;
    }
}
