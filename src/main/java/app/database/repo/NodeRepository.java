package app.database.repo;

import app.database.model.node.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository, providing access to nodes in the database.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {

    /**
     * Searches the first node
     *
     * @return first node
     */
    Node findFirstByOrderById();
}
