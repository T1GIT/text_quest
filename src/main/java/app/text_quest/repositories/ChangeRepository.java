package app.text_quest.repositories;

import app.text_quest.models.Change;
import app.text_quest.models.Node.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChangeRepository extends JpaRepository<Change, Long> {
    List<Change> findByAnswerId(long answerId);

    List<Change> findByVarId(long varId);
}
