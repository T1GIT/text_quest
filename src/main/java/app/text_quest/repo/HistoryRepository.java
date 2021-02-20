package app.text_quest.repo;

import app.text_quest.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Deprecated
@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUserId(long userId);

//    List<History> findAllTop10(); TODO:
}
