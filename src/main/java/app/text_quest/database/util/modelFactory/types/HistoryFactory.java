package app.text_quest.database.util.modelFactory.types;

import app.text_quest.database.model.History;
import app.text_quest.database.model.node.Node;
import app.text_quest.database.model.user.User;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;
import app.text_quest.database.util.modelFactory.types.node.NodeFactory;


/**
 * @see app.text_quest.database.util.modelFactory.AbstractModelFactory
 */
public class HistoryFactory extends AbstractModelFactory<History> {

    private final static NodeFactory nodeFactory = new NodeFactory();
    private final static UserFactory userFactory = new UserFactory();
    private final Node node = nodeFactory.create();
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
