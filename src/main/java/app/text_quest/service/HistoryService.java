package app.text_quest.service;


import app.text_quest.model.History;
import app.text_quest.model.User;


import java.util.List;

@Deprecated
public interface HistoryService {
    History addHistory(History history);
    void delete(History history);
    List<History> getByUser(User user);
    History editHistory(History history);
    List<History> getAll();
}
