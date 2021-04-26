package app.database.service.userService.types;

import app.database.model.user.types.OauthUser;
import app.database.util.abstractService.ServiceInterface;


/**
 * Service for oauth users
 *
 * @see app.database.util.abstractService.ServiceInterface
 */
public interface OauthUserService extends ServiceInterface<OauthUser> {

    /**
     * Gets oauth user by its oauth id.
     *
     * @param oauthId id for searching
     * @return oauth user model or null if didn't found
     */
    OauthUser getByOauthId(String oauthId);
}
