package app.text_quest.database.repo;

import app.text_quest.database.model.Var;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VarRepository extends JpaRepository<Var, Long> {
    Var findByName(String name);
}
