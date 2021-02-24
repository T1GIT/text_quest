package app.text_quest.database.repo;

import app.text_quest.database.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Deprecated
@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findByForkId(long forkId);
}
