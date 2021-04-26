package app.database.service.impl;

import app.database.model.Refresh;
import app.database.model.user.User;
import app.database.repo.RefreshRepository;
import app.database.service.RefreshService;
import app.database.util.abstractService.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @see app.database.service.RefreshService
 */
@Service
@Transactional
public class RefreshServiceImpl
        extends AbstractService<Refresh, RefreshRepository>
        implements RefreshService {

    public RefreshServiceImpl(RefreshRepository repository) {
        super(repository);
    }

    @Override
    public List<Refresh> getByUser(User user) {
        return repository.findAllByUserId(user.getId());
    }

    @Override
    public Refresh getByValue(String value) {
        return repository.findByValue(value);
    }
}
