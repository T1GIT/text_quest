package app.text_quest.util.modelCreator;

import app.text_quest.model.Limit;
import app.text_quest.model.Psw;

public class LimitCreator {
    public static Limit create() {
        Limit limit = new Limit();
        limit.setBranch(BranchCreator.create());
        limit.setMax(2);
        limit.setEqual(1);
        limit.setMin(0);
        limit.setVar(VarCreator.create());
        return limit;
    }
}
