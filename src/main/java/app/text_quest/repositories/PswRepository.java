package app.text_quest.repositories;

import app.text_quest.models.Node.Node;
import app.text_quest.models.Psw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PswRepository extends JpaRepository<Psw, Long> {
    Psw findByUserId(long userId);
}
