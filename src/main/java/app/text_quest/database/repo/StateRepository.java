package app.text_quest.database.repo;

import app.text_quest.database.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Deprecated
@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findByUserId(long userId);

    State findByUserIdAndVarId(long userId, long varId);
}
