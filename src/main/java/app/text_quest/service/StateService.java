package app.text_quest.service;

import app.text_quest.model.State;
import app.text_quest.model.User;
import app.text_quest.model.Var;

import java.util.List;

public interface StateService {
    State addState(State state);
    void delete(State state);
    List<State> getByUser(User user);
    State getByUserAndVar(User user, Var var);
    State editState(State state);
    List<State> getAll();
}
