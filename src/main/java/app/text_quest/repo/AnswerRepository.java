package app.text_quest.repo;

import app.text_quest.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Deprecated
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByOutMsgId(long outMsgId);
}
