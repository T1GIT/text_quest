package app.text_quest.database.service;

import app.text_quest.database.model.Var;
import app.text_quest.database.util.abstractService.ServiceInterface;


/**
 Service for variables

 @see app.text_quest.database.util.abstractService.ServiceInterface */
public interface VarService extends ServiceInterface<Var> {

    /**
     Gets a variable by its name.

     @param name of the variable
     @return variable model or null if didn't found
     */
    Var getByName(String name);
}
