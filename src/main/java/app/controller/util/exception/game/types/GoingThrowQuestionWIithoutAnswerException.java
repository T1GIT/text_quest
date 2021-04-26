package app.controller.util.exception.game.types;

import app.controller.util.exception.game.GameException;
import app.database.model.node.Node;
import app.database.model.node.types.LinkedNode.types.Question;


/**
 * Exception throwing if JWT was missed in the cookie
 */
public class GoingThrowQuestionWIithoutAnswerException extends GameException {

    public GoingThrowQuestionWIithoutAnswerException(Question question) {
        super("Trying to get next node from question without answer " + question);
    }
}
