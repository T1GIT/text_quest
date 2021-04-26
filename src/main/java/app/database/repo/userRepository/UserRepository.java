package app.database.repo.userRepository;

import app.database.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository, providing access to users in the database.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
