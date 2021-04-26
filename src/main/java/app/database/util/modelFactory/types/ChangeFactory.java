package app.database.util.modelFactory.types;

import app.database.model.Answer;
import app.database.model.Change;
import app.database.model.Var;
import app.database.util.modelFactory.AbstractModelFactory;


/**
 * @see app.database.util.modelFactory.AbstractModelFactory
 */
public class ChangeFactory extends AbstractModelFactory<Change> {

    private final static AnswerFactory answerFactory = new AnswerFactory();
    private final static VarFactory varFactory = new VarFactory();
    private final Answer answer = answerFactory.create();
    private final Var var = varFactory.create();
    private final int val = 10;

    public Change create() {
        Change change = new Change();
        change.setAnswer(answerFactory.create());
        change.setVal(this.val);
        change.setVar(varFactory.create());
        return change;
    }

    public Answer getAnswer() {
        return answer;
    }

    public Var getVar() {
        return var;
    }

    public int getVal() {
        return val;
    }
}
