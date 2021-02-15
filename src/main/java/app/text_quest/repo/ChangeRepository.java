package app.text_quest.repo;

import app.text_quest.model.Change;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangeRepository extends JpaRepository<Change, Long> {
    List<Change> findByAnswerId(long answerId);

    Change findByAnswerIdAndVarId(long answerId, long varId);
}
