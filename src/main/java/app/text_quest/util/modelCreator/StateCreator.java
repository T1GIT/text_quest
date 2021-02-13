package app.text_quest.util.modelCreator;

import app.text_quest.model.Psw;
import app.text_quest.model.State;

public class StateCreator {
    public static State create() {
        State state = new State();
        state.setUser(UserCreator.create());
        state.setVal(0);
        state.setVar(VarCreator.create());
        return state;
    }
}
