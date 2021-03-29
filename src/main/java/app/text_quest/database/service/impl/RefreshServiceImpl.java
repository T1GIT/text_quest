package app.text_quest.database.service.impl;

import app.text_quest.database.model.Refresh;
import app.text_quest.database.model.user.User;
import app.text_quest.database.repo.RefreshRepository;
import app.text_quest.database.service.RefreshService;
import app.text_quest.database.util.abstractService.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @see app.text_quest.database.service.RefreshService
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

    @Override
    public void deleteBefore(Date date) {
        repository.deleteAllByCreatedAtBefore(date);
    }
}
