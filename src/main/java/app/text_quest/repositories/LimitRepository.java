package app.text_quest.repositories;

import app.text_quest.models.Change;
import app.text_quest.models.Limit;
import app.text_quest.models.Node.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LimitRepository extends JpaRepository<Limit, Long> {
    List<Limit> findByBranchId(long branchId);

    List<Limit> findByVarId(long varId);
}
