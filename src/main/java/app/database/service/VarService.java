package app.database.service;

import app.database.model.Var;
import app.database.util.abstractService.ServiceInterface;


/**
 * Service for variables
 *
 * @see app.database.util.abstractService.ServiceInterface
 */
public interface VarService extends ServiceInterface<Var> {

    /**
     * Gets a variable by its name.
     *
     * @param name of the variable
     * @return variable model or null if didn't found
     */
    Var getByName(String name);
}
