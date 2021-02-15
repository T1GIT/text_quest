package app.text_quest.service.impl;


import app.text_quest.model.Answer;
import app.text_quest.model.Change;
import app.text_quest.model.Var;
import app.text_quest.repo.ChangeRepository;
import app.text_quest.service.ChangeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ChangeServiceImpl implements ChangeService {
    private final ChangeRepository changeRepository;

    public ChangeServiceImpl(ChangeRepository changeRepository) {
        this.changeRepository = changeRepository;
    }

    @Override
    public Change addChange(Change change) {
        return changeRepository.saveAndFlush(change);
    }

    @Override
    public void delete(Change change) {
        changeRepository.delete(change);
    }

    @Override
    public List<Change> getByAnswer(Answer answer) {
        return changeRepository.findByAnswerId(answer.getId());
    }

    @Override
    public Change getByAnswerAndVar(Answer answer, Var var) {
        return changeRepository.findByAnswerIdAndVarId(answer.getId(), var.getId());
    }

    @Override
    public Change editChange(Change change) {
        return changeRepository.saveAndFlush(change);
    }

    @Override
    public List<Change> getAll() {
        return changeRepository.findAll();
    }
}
