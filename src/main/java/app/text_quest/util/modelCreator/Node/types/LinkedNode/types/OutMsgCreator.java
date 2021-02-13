package app.text_quest.util.modelCreator.Node.types.LinkedNode.types;

import app.text_quest.model.Node.types.LinkedNode.types.OutMsg;
import app.text_quest.util.modelCreator.Node.NodeCreator;

public class OutMsgCreator {
    public static OutMsg create() {
        OutMsg outMsg = new OutMsg();
        outMsg.setNextNode(NodeCreator.create());
        return outMsg;
    }
}
