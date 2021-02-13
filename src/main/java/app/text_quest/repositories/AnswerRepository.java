package app.text_quest.repositories;

import app.text_quest.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByOutMsgId(long outMsgId);

    List<Answer> findByTextId(long textId);
}
