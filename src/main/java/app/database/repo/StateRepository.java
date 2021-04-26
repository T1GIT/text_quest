package app.database.repo;

import app.database.model.State;
import app.database.model.Var;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository, providing access to variables in the database.
 *
 * @see JpaRepository
 */
@Repository
public interface StateRepository extends JpaRepository<State, Long> {


    /**
     * Searches all the user's states
     *
     * @param userId user's id
     * @return list of states
     */
    List<State> findAllByUserId(long userId);
}
