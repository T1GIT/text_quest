package app.text_quest.database.repo;

import app.text_quest.database.model.Psw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Deprecated
@Repository
public interface PswRepository extends JpaRepository<Psw, Long> {
    Psw findByUserId(long userId);
}
