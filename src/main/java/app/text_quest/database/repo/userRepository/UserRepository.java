package app.text_quest.database.repo.userRepository;

import app.text_quest.database.model.user.User;
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
