package app.text_quest.database.service;

import app.text_quest.database.model.Var;

import java.util.List;

public interface VarService {
    Var addVar(Var var);
    void delete(Var var);
    Var getByName(String name);
    Var editVar(Var var);
    List<Var> getAll();
}
