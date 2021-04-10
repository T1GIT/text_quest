package app.database.util.modelFactory.types.node.types.LinkedNode.types;

import app.database.model.node.Node;
import app.database.model.node.types.LinkedNode.types.Question;
import app.database.util.modelFactory.AbstractModelFactory;
import app.database.util.modelFactory.types.node.NodeFactory;


/**
 * @see app.database.util.modelFactory.AbstractModelFactory
 */
public class QuestionFactory extends AbstractModelFactory<Question> {

    private final static NodeFactory nodeFactory = new NodeFactory();
    private final Node node = nodeFactory.create();

    public Question create() {
        Question question = new Question();
        question.setNextNode(nodeFactory.create());
        return question;
    }

    public Node getNode() {
        return node;
    }
}
