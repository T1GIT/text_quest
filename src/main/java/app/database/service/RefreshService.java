package app.database.service;

import app.database.model.Refresh;
import app.database.model.user.User;
import app.database.util.abstractService.ServiceInterface;

import java.util.List;


/**
 * Service for refresh tokens
 *
 * @see app.database.util.abstractService.ServiceInterface
 */
public interface RefreshService extends ServiceInterface<Refresh> {// TODO: 04.03.2021 Add fingerprint. Storage with tokens

    /**
     * Gets all user's refresh tokens.
     *
     * @param user for collecting refresh tokens
     * @return list of user's tokens
     */
    List<Refresh> getByUser(User user);

    /**
     * Gets token by its name.
     *
     * @param value for searching
     * @return refresh token model or null if didn't found
     */
    Refresh getByValue(String value);
}
