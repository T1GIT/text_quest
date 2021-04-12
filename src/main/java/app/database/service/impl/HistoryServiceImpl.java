package app.database.service.impl;

import app.database.model.History;
import app.database.model.user.User;
import app.database.repo.HistoryRepository;
import app.database.service.HistoryService;
import app.database.service.NodeService;
import app.database.util.abstractService.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


/**
 * @see NodeService
 */
@Service
@Transactional
public class HistoryServiceImpl
        extends AbstractService<History, HistoryRepository>
        implements HistoryService {

    public HistoryServiceImpl(HistoryRepository repository) {
        super(repository);
    }

    @Override
    public List<History> getLastNodes(User user) {
        List<History> historyList = repository.findFirst10ByUserIdOrderByCreatedAtDesc(user.getId());
        Collections.reverse(historyList);
        return historyList;
    }
}
