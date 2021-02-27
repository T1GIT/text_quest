package app.text_quest.database.service;


import app.text_quest.database.model.node.Node;

import java.util.List;


public interface NodeService {
    Node addNode(Node node);
    void delete(Node node);
    Node editNode(Node node);
    List<Node> getAll();
}
