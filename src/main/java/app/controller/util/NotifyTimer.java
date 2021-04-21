package app.controller.util;

import app.controller.util.constant.CurrentNodeType;
import app.controller.util.constant.Period;
import app.controller.util.exception.game.types.NodeTypeException;
import app.database.model.History;
import app.database.model.node.Node;
import app.database.model.node.types.LinkedNode.LinkedNode;
import app.database.model.node.types.LinkedNode.types.Message;
import app.database.model.node.types.LinkedNode.types.Question;
import app.database.model.user.User;
import app.database.service.HistoryService;
import app.database.service.NodeService;
import app.database.service.StateService;
import app.database.service.userService.UserService;
import org.hibernate.Hibernate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Timer;
import java.util.TimerTask;

public class NotifyTimer extends TimerTask {

    private final User user;
    private final String socketId;
    private final SimpMessagingTemplate template;
    private final Tree tree;
    private final NodeService nodeService;
    private final HistoryService historyService;

    public NotifyTimer(String socketId, User user, SimpMessagingTemplate template, Tree tree, NodeService nodeService, HistoryService historyService) {
        this.socketId = socketId;
        this.user = user;
        this.template = template;
        this.tree = tree;
        this.nodeService = nodeService;
        this.historyService = historyService;
    }

    @Transactional
    @Override
    public void run() {
        LinkedNode lastNode = historyService.getLast(user).getNode();
        if (lastNode.getClass() != Question.class) {
            LinkedNode nextNode = tree.nextNode(
                    Hibernate.unproxy(lastNode, LinkedNode.class),
                    user);
            historyService.add(new History(user, nextNode));
            push(nextNode);
        }
    }

    private void push(Node nextNode) {
        String payload;
        if (nextNode.getClass() == Question.class) {
            payload = CurrentNodeType.QUESTION;
        } else if (nextNode.getClass() == Message.class) {
            payload = CurrentNodeType.MESSAGE;
            TimerTask task = new NotifyTimer(socketId, user, template, tree, nodeService, historyService);
            new Timer().schedule(task, Period.getMillis(((Message) nextNode).getDelay()));
        } else {
            throw new NodeTypeException(nextNode.getClass());
        }
        template.convertAndSendToUser(socketId, "/next_node", payload);
    }
}
