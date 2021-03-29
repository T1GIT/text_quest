package app.text_quest.database.util.modelFactory.types.node.types.LinkedNode.types;

import app.text_quest.database.model.node.Node;
import app.text_quest.database.model.node.types.LinkedNode.types.Message;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;
import app.text_quest.database.util.modelFactory.types.node.NodeFactory;


/**
 * @see app.text_quest.database.util.modelFactory.AbstractModelFactory
 */
public class MessageFactory extends AbstractModelFactory<Message> {

    private final static NodeFactory nodeFactory = new NodeFactory();
    private final Node nextNode = nodeFactory.create();
    private final int delay = 1000;
    private final String text = "text";

    public Message create() {
        Message message = new Message();
        message.setDelay(this.delay);
        message.setText(text);
        message.setNextNode(nodeFactory.create());
        return message;
    }

    public String getText() {
        return text;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public int getDelay() {
        return delay;
    }
}
