package app.database.service.userService;

import app.database.model.user.User;
import app.database.util.abstractService.ServiceInterface;


/**
 * Service for variables
 *
 * @see app.database.util.abstractService.ServiceInterface
 */
public interface UserService extends ServiceInterface<User> {

    /**
     * Checks if user is administrator.
     *
     * @param user to check
     * @return true if user is administrator
     */
    boolean isAdmin(User user);

}
