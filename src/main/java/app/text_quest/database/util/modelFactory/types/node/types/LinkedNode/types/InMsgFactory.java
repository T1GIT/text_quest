package app.text_quest.database.util.modelFactory.types.node.types.LinkedNode.types;

import app.text_quest.database.model.msg.Msg;
import app.text_quest.database.model.node.Node;
import app.text_quest.database.model.node.types.LinkedNode.types.InMsg;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;
import app.text_quest.database.util.modelFactory.types.msg.MsgFactory;
import app.text_quest.database.util.modelFactory.types.node.NodeFactory;


/**
 @see app.text_quest.database.util.modelFactory.AbstractModelFactory */
public class InMsgFactory extends AbstractModelFactory<InMsg> {

    private final static MsgFactory msgFactory = new MsgFactory();
    private final static NodeFactory nodeFactory = new NodeFactory();
    private final Msg msg = msgFactory.create();
    private final Node nextNode = nodeFactory.create();
    private final int delay = 1000;

    public InMsg create() {
        InMsg inMsg = new InMsg();
        inMsg.setDelay(this.delay);
        inMsg.setMsg(msgFactory.create());
        inMsg.setNextNode(nodeFactory.create());
        return inMsg;
    }

    public Msg getMsg() {
        return msg;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public int getDelay() {
        return delay;
    }
}
