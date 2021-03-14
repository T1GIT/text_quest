package app.text_quest.database.util.modelFactory.types.node;

import app.text_quest.database.model.node.Node;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;

public class NodeFactory extends AbstractModelFactory<Node> {
    public Node create() {
        return new Node();
    }
}
