package app.text_quest.database.repo;

import app.text_quest.database.model.node.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository, providing access to nodes in the database.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
}
