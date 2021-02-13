package app.text_quest.repositories;

import app.text_quest.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findByOutMsgId(int outMsgId);

    List<Answer> findByTextId(int textId);
}
