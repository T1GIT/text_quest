package app.text_quest.service.impl;

import app.text_quest.model.History;
import app.text_quest.model.User;
import app.text_quest.repo.HistoryRepository;
import app.text_quest.service.HistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Deprecated
@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public History addHistory(History history) {
        return historyRepository.saveAndFlush(history);
    }

    @Override
    public void delete(History history) {
        historyRepository.delete(history);
    }

    @Override
    public List<History> getByUser(User user) {
        return historyRepository.findByUserId(user.getId());
    }

    @Override
    public History editHistory(History history) {
        return historyRepository.saveAndFlush(history);
    }

    @Override
    public List<History> getAll() {
        return historyRepository.findAll();
    }
}
