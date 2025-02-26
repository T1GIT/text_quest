package app.database.service.userService.types;


import app.database.model.user.types.MailUser;
import app.database.util.abstractService.ServiceInterface;


/**
 * Service for mail users
 *
 * @see app.database.util.abstractService.ServiceInterface
 */
public interface MailUserService extends ServiceInterface<MailUser> {

    /**
     * Gets mail user by its email.
     *
     * @param mail email for searching
     * @return mail user model or null if didn't found
     */
    MailUser getByMail(String mail);
}
