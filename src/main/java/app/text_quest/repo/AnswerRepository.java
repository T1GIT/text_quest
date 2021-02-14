package app.text_quest.repo;

import app.text_quest.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByOutMsgId(long outMsgId);

    List<Answer> findByTextId(long textId);
}
