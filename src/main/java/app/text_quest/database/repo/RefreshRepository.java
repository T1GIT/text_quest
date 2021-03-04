package app.text_quest.database.repo;

import app.text_quest.database.model.Refresh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface RefreshRepository extends JpaRepository<Refresh, Long> {

    Refresh findByUserId(long userId);

    Refresh findByValue(String value);

    void deleteAllByCreatedAtBefore(Date date);
}
