package app.text_quest.repositories;

import app.text_quest.models.Branch;
import app.text_quest.models.Node.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findByForkId(long forkId);

    List<Branch> findByNodeId(long forkId);
}
