package app.text_quest.repositories;

import app.text_quest.models.Node.Node;
import app.text_quest.models.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findByUserId(long userId);

    State findByUserIdAndVarId(long userId, long varId);
}
