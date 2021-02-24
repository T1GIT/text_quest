package app.text_quest.database.service.impl;

import app.text_quest.database.model.State;
import app.text_quest.database.model.User;
import app.text_quest.database.model.Var;
import app.text_quest.database.repo.StateRepository;
import app.text_quest.database.service.StateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Deprecated
@Service
@Transactional
public class StateServiceImpl implements StateService {
    private final StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public State addState(State state) {
        return this.stateRepository.saveAndFlush(state);
    }

    @Override
    public void delete(State state) {
        this.stateRepository.delete(state);
    }

    @Override
    public List<State> getByUser(User user) {
        return this.stateRepository.findByUserId(user.getId());
    }

    @Override
    public State getByUserAndVar(User user, Var var) {
        return this.stateRepository.findByUserIdAndVarId(user.getId(), var.getId());
    }

    @Override
    public State editState(State state) {
        return this.stateRepository.saveAndFlush(state);
    }

    @Override
    public List<State> getAll() {
        return this.stateRepository.findAll();
    }
}
