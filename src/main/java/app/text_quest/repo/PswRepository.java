package app.text_quest.repo;

import app.text_quest.model.Psw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PswRepository extends JpaRepository<Psw, Long> {
    Psw findByUserId(long userId);
}
