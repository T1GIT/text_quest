package app.database.repo;

import app.database.model.Refresh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository, providing access to refresh tokens in the database.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Repository
public interface RefreshRepository extends JpaRepository<Refresh, Long> {

    /**
     * Searches tokens by user id.
     *
     * @param userId user id for searching
     * @return list of user's tokens
     */
    List<Refresh> findAllByUserId(long userId);

    /**
     * Searches token by its value.
     *
     * @param value for searching
     * @return token model or null if didn't found
     */
    Refresh findByValue(String value);

}
