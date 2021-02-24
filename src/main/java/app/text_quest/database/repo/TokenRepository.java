package app.text_quest.database.repo;

import app.text_quest.database.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Deprecated
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByUserId(long userId);

    void deleteAllByCreatedAtBefore(Date date);
}
