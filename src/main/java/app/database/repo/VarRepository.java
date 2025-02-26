package app.database.repo;

import app.database.model.Var;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository, providing access to variables in the database.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Repository
public interface VarRepository extends JpaRepository<Var, Long> {


    /**
     * Searches variable by its name
     *
     * @param name of the variable
     * @return variable model or null if didn't found
     */
    Var findByName(String name);
}
