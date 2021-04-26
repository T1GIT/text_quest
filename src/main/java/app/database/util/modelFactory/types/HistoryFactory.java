package app.database.util.modelFactory.types;

import app.database.model.History;
import app.database.model.node.Node;
import app.database.model.node.types.LinkedNode.LinkedNode;
import app.database.model.user.User;
import app.database.util.modelFactory.AbstractModelFactory;
import app.database.util.modelFactory.types.node.NodeFactory;
import app.database.util.modelFactory.types.node.types.LinkedNode.LinkedNodeFactory;


/**
 * @see app.database.util.modelFactory.AbstractModelFactory
 */
public class HistoryFactory extends AbstractModelFactory<History> {

    private final static LinkedNodeFactory nodeFactory = new LinkedNodeFactory();
    private final static UserFactory userFactory = new UserFactory();
    private final LinkedNode node = nodeFactory.create();
    private final User user = userFactory.create();


    public History create() {
        History history = new History();
        history.setNode(nodeFactory.create());
        history.setUser(userFactory.create());
        return history;
    }

    public Node getNode() {
        return node;
    }

    public User getUser() {
        return user;
    }
}
