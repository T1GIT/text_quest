package app.text_quest.repo;

import app.text_quest.model.msg.Msg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MsgRepository extends JpaRepository<Msg, Long> {
}
