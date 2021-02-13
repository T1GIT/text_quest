package app.text_quest.repositories;

import app.text_quest.models.Limit;
import app.text_quest.models.Msg.Msg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MsgRepository extends JpaRepository<Msg, Long> {
}
