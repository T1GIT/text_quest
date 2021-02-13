package app.text_quest.util.modelCreator;

import app.text_quest.model.Change;
import app.text_quest.model.Psw;

public class ChangeCreator {
    public static Change create() {
        Change change = new Change();
        change.setAnswer(AnswerCreator.create());
        change.setVal(0);
        change.setVar(VarCreator.create());
        return change;
    }
}
