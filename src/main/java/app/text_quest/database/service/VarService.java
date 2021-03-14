package app.text_quest.database.service;

import app.text_quest.database.model.Var;
import app.text_quest.database.util.AbstractService;


public interface VarService extends AbstractService<Var> {

    Var getByName(String name);
}
