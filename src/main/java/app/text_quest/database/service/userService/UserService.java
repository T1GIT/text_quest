package app.text_quest.database.service.userService;

import app.text_quest.database.model.user.User;
import app.text_quest.database.util.abstractService.ServiceInterface;


/**
 * Service for variables
 *
 * @see app.text_quest.database.util.abstractService.ServiceInterface
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
