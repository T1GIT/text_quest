package app.text_quest.service;




import app.text_quest.model.Answer;
import app.text_quest.model.Change;
import app.text_quest.model.Var;

import java.util.List;

@Deprecated
public interface ChangeService {
    Change addChange(Change change);
    void delete(Change change);
    List<Change> getByAnswer(Answer answer);
    Change getByAnswerAndVar(Answer answer, Var var);
    Change editChange(Change change);
    List<Change> getAll();
}
