package app.text_quest.database.service.impl;

import app.text_quest.database.model.Answer;
import app.text_quest.database.model.node.types.LinkedNode.types.OutMsg;
import app.text_quest.database.repo.AnswerRepository;
import app.text_quest.database.service.AnswerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Deprecated
@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer addAnswer(Answer answer) {
        return this.answerRepository.saveAndFlush(answer);
    }

    @Override
    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }

    @Override
    public List<Answer> getByOutMsg(OutMsg outMsg) {
        return this.answerRepository.findByOutMsgId(outMsg.getId());
    }

    @Override
    public Answer editAnswer(Answer answer) {
        return this.answerRepository.saveAndFlush(answer);
    }

    @Override
    public List<Answer> getAll() {
        return this.answerRepository.findAll();
    }
}
