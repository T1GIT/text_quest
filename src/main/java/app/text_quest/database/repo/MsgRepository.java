package app.text_quest.database.repo;

import app.text_quest.database.model.msg.Msg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository, providing access to messages in the database.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Repository
public interface MsgRepository extends JpaRepository<Msg, Long> {
}
