package app.text_quest.repo;

import app.text_quest.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Deprecated
@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
    List<Limit> findByBranchId(long branchId);

    Limit findByBranchIdAndVarId(long branchId, long varId);
}
