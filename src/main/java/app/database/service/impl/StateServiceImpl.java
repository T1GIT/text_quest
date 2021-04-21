package app.database.service.impl;

import app.database.model.State;
import app.database.model.user.User;
import app.database.repo.StateRepository;
import app.database.service.StateService;
import app.database.util.abstractService.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @see StateService
 */
@Service
@Transactional
public class StateServiceImpl
        extends AbstractService<State, StateRepository>
        implements StateService {

    public StateServiceImpl(StateRepository repository) {
        super(repository);
    }

    @Override
    public List<State> getByUser(User user) {
        return repository.findAllByUserId(user.getId());
    }
}
