package app.database.service;


import app.database.model.History;
import app.database.model.node.Node;
import app.database.model.user.User;
import app.database.util.abstractService.ServiceInterface;

import java.util.List;


/**
 * Service for histories
 *
 * @see ServiceInterface
 */
public interface HistoryService extends ServiceInterface<History> {


    /**
     * Gets last 10 nodes from the database
     *
     * @return 10 last nodes
     */
    List<History> getLastNodes(User user);

}
