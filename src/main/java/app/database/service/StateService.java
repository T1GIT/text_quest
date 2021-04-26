package app.database.service;


import app.database.model.State;
import app.database.model.user.User;
import app.database.util.abstractService.ServiceInterface;

import java.util.List;


/**
 * Service for states
 *
 * @see ServiceInterface
 */
public interface StateService extends ServiceInterface<State> {


    /**
     * Gets all the states of the user
     *
     * @return list of states
     * @param user to searching
     */
    List<State> getByUser(User user);

}
