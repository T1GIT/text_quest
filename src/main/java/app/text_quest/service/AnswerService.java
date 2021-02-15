package app.text_quest.service;

import app.text_quest.model.Answer;
import app.text_quest.model.node.types.Fork;
import app.text_quest.model.node.types.LinkedNode.types.OutMsg;

import java.util.List;

public interface AnswerService {
    Answer addAnswer(Answer answer);
    void delete(Answer answer);
    List<Answer> getByOutMsg(OutMsg outMsg);
    Answer editAnswer(Answer answer);
    List<Answer> getAll();
}
