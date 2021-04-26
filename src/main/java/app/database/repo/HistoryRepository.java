package app.database.repo;

import app.database.model.History;
import app.database.model.node.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository, providing access to histories in the database.
 *
 * @see JpaRepository
 */
@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findFirst10ByUserIdOrderByCreatedAtDesc(long userId);

    History findFirstByUserIdOrderByCreatedAtDesc(long userId);
}
