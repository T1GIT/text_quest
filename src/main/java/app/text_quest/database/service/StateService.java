package app.text_quest.database.service;

import app.text_quest.database.model.State;
import app.text_quest.database.model.User;
import app.text_quest.database.model.Var;

import java.util.List;

@Deprecated
public interface StateService {
    State addState(State state);

    void delete(State state);

    List<State> getByUser(User user);

    State getByUserAndVar(User user, Var var);

    State editState(State state);

    List<State> getAll();
}
