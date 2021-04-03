package app.text_quest.database.repo;

import app.text_quest.database.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository, providing access to variables in the database.
 *
 * @see JpaRepository
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {


    /**
     * Checks if admin with this id is in the table
     *
     * @param adminId id of the user to check existing
     * @return variable model or null if didn't found
     */
    boolean existsById(long adminId);
}
