package app.text_quest.repositories;

import app.text_quest.models.Node.Node;
import app.text_quest.models.Var;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VarRepository extends JpaRepository<Var, Long> {
    Var findByNameIs(String name);
}
