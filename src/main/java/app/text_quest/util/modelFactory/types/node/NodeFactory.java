package app.text_quest.util.modelFactory.types.node;

import app.text_quest.model.node.Node;
import app.text_quest.util.modelFactory.AbstractModelFactory;

public class NodeFactory extends AbstractModelFactory<Node> {
    public Node create() {
        return new Node();
    }
}
