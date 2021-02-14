package app.text_quest.util.modelFactory.types;

import app.text_quest.model.State;
import app.text_quest.model.User;
import app.text_quest.model.Var;
import app.text_quest.util.modelFactory.AbstractModelFactory;

public class StateFactory extends AbstractModelFactory<State> {
    private final static UserFactory userFactory = new UserFactory();
    private final static VarFactory varFactory = new VarFactory();
    private final User user = userFactory.create();
    private final Var var = varFactory.create();
    private final int val = 11;

    public State create() {
        State state = new State();
        state.setUser(userFactory.create());
        state.setVal(this.val);
        state.setVar(varFactory.create());
        return state;
    }

    public User getUser() {
        return user;
    }

    public Var getVar() {
        return var;
    }

    public int getVal() {
        return val;
    }
}
