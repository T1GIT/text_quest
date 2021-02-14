package app.text_quest.repo;

import app.text_quest.model.Var;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VarRepository extends JpaRepository<Var, Long> {
    Var findByNameIs(String name);
}
