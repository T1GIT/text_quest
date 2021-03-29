package app.text_quest.database.util.modelFactory.types;

import app.text_quest.database.model.Answer;
import app.text_quest.database.model.node.types.LinkedNode.types.Question;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;
import app.text_quest.database.util.modelFactory.types.node.types.LinkedNode.types.QuestionFactory;


/**
 * @see app.text_quest.database.util.modelFactory.AbstractModelFactory
 */
public class AnswerFactory extends AbstractModelFactory<Answer> {

    private final static QuestionFactory QUESTION_FACTORY = new QuestionFactory();
    private final Question question = QUESTION_FACTORY.create();
    private final String text = "text";

    public Answer create() {
        Answer answer = new Answer();
        answer.setQuestion(QUESTION_FACTORY.create());
        answer.setText(text);
        return answer;
    }

    public Question getOutMsg() {
        return question;
    }

    public String getText() {
        return text;
    }
}
