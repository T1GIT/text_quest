package app.text_quest.database.service;

import app.text_quest.database.model.Refresh;
import app.text_quest.database.model.user.User;
import app.text_quest.database.util.abstractService.ServiceInterface;

import java.util.Date;
import java.util.List;


/**
 * Service for refresh tokens
 *
 * @see app.text_quest.database.util.abstractService.ServiceInterface
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

    /**
     * Deletes all old data before the given date
     *
     * @param date bound for searching
     */
    void deleteBefore(Date date);
}
