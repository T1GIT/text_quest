package app.text_quest.util.modelCreator.Node.types.LinkedNode;

import app.text_quest.model.Node.types.LinkedNode.LinkedNode;
import app.text_quest.util.modelCreator.Node.NodeCreator;

public class LinkedNodeCreator {
    public static LinkedNode create() {
        LinkedNode linkedNode = new LinkedNode();
        linkedNode.setNextNode(NodeCreator.create());
        return linkedNode;
    }
}
