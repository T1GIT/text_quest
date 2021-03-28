package app.text_quest.database.repo.userRepository;

import app.text_quest.database.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 <h2> An interface {@link UserRepository}

 <p> Provides accessing to users in the database

 @see org.springframework.data.jpa.repository.JpaRepository */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
