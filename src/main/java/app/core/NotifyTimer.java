package app.core;

import app.controller.util.constant.NodeType;
import app.controller.util.constant.Period;
import app.controller.util.exception.game.types.FinishException;
import app.controller.util.exception.game.types.NodeTypeException;
import app.database.model.History;
import app.database.model.node.Node;
import app.database.model.node.types.LinkedNode.LinkedNode;
import app.database.model.node.types.LinkedNode.types.Message;
import app.database.model.node.types.LinkedNode.types.Question;
import app.database.model.user.User;
import app.database.service.HistoryService;
import app.database.service.NodeService;
import org.hibernate.Hibernate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class NotifyTimer extends TimerTask {

    private static final Map<String, Timer> timers = new HashMap<>();

    private final User user;
    private final SimpMessagingTemplate template;
    private final Tree tree;
    private final NodeService nodeService;
    private final HistoryService historyService;

    public NotifyTimer(User user, SimpMessagingTemplate template, Tree tree, NodeService nodeService, HistoryService historyService) {
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
            try {
                LinkedNode nextNode = tree.nextNode(
                        Hibernate.unproxy(lastNode, LinkedNode.class),
                        user);
                historyService.add(new History(user, nextNode));
                push(nextNode);
            } catch (FinishException e) {
                historyService.add(new History(user, null));
            }
        }
    }

    private void push(Node nextNode) {
        String payload;
        if (nextNode.getClass() == Question.class) {
            payload = NodeType.QUESTION;
        } else if (nextNode.getClass() == Message.class) {
            payload = NodeType.MESSAGE;
            NotifyTimer.start(user, template, tree, nodeService, historyService);
        } else {
            throw new NodeTypeException(nextNode.getClass());
        }
        template.convertAndSendToUser(user.getSocketId(), "/next_node", payload);
    }

    public static void start(User user, SimpMessagingTemplate template, Tree tree, NodeService nodeService, HistoryService historyService) {
        Timer timer = new Timer();
        TimerTask task = new NotifyTimer(user, template, tree, nodeService, historyService);
        Node node = historyService.getLast(user).getNode();
        if (node.getClass() == Message.class) {
            timer.schedule(task, Period.getMillis((((Message) node).getDelay())));
        } else {
            timer.schedule(task, Period.getMillis(1));
        }
        timers.put(user.getSocketId(), timer);
    }

    public static void stop(User user) {
        timers.get(user.getSocketId()).cancel();
    }
}
