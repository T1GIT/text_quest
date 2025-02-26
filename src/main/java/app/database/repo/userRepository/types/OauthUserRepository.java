package app.database.repo.userRepository.types;

import app.database.model.user.types.OauthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository, providing access to oauth users in the database.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Repository
public interface OauthUserRepository extends JpaRepository<OauthUser, Long> {

    /**
     * Searches oauth user by its oauth id
     *
     * @param oauthId oauth id for searching
     * @return oauth user model or null if didn't found
     */
    OauthUser findByOauthId(String oauthId);
}
