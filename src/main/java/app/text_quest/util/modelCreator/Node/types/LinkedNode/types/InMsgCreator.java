package app.text_quest.util.modelCreator.Node.types.LinkedNode.types;

import app.text_quest.model.Node.types.LinkedNode.types.InMsg;
import app.text_quest.util.modelCreator.Msg.MsgCreator;
import app.text_quest.util.modelCreator.Node.NodeCreator;

public class InMsgCreator {
    public static InMsg create() {
        InMsg inMsg = new InMsg();
        inMsg.setDelay(0);
        inMsg.setMsg(MsgCreator.create());
        inMsg.setNextNode(NodeCreator.create());
        return inMsg;
    }
}
