package app.text_quest.repositories;

import app.text_quest.models.Node.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<Node, Long> {
}
