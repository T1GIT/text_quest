package app.text_quest.database.util.modelFactory.types.node.types.LinkedNode.types;

import app.text_quest.database.model.node.Node;
import app.text_quest.database.model.node.types.LinkedNode.types.Question;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;
import app.text_quest.database.util.modelFactory.types.node.NodeFactory;


/**
 * @see app.text_quest.database.util.modelFactory.AbstractModelFactory
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
