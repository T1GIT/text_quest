package app.text_quest.repositories;

import app.text_quest.models.Node.Node;
import app.text_quest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);
}
