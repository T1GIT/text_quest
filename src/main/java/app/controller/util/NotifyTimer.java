package app.controller.util;

import app.controller.util.constant.CurrentNodeType;
import app.controller.util.constant.Period;
import app.controller.util.exception.game.types.FinishException;
import app.controller.util.exception.game.types.GoingThrowQuestionWIithoutAnswerException;
import app.controller.util.exception.game.types.ModelNotFoundException;
import app.controller.util.exception.game.types.NodeTypeException;
import app.database.model.Branch;
import app.database.model.History;
import app.database.model.Limit;
import app.database.model.State;
import app.database.model.node.Node;
import app.database.model.node.types.Fork;
import app.database.model.node.types.LinkedNode.LinkedNode;
import app.database.model.node.types.LinkedNode.types.Message;
import app.database.model.node.types.LinkedNode.types.Question;
import app.database.model.user.User;
import app.database.service.HistoryService;
import app.database.service.NodeService;
import app.database.service.userService.UserService;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NotifyTimer extends TimerTask {

    private final String socketId;
    private final long userId;
    private final SimpMessagingTemplate template;
    private final UserService userService;
    private final NodeService nodeService;
    private final HistoryService historyService;

    public NotifyTimer(String socketId, long userId, SimpMessagingTemplate template, UserService userService, NodeService nodeService, HistoryService historyService) {
        this.socketId = socketId;
        this.userId = userId;
        this.template = template;
        this.userService = userService;
        this.nodeService = nodeService;
        this.historyService = historyService;
    }

    private void push(Node nextNode) {
        String payload;
        if (nextNode.getClass() == Question.class) {
            payload = CurrentNodeType.QUESTION;
        } else if (nextNode.getClass() == Message.class) {
            payload = CurrentNodeType.MESSAGE;
            TimerTask task = new NotifyTimer(socketId, userId, template, userService, nodeService, historyService);
            new Timer().schedule(task, Period.getMillis(((Message) nextNode).getDelay()));
        } else {
            throw new NodeTypeException(nextNode.getClass());
        }
        template.convertAndSendToUser(socketId, "/next_node", payload);
    }

    private void addHistory(User user, Node nextNode) {
        History history = new History();
        history.setNode(nextNode);
        history.setUser(user);
        historyService.add(history);
    }

    @Override
    public void run() {
        User user = userService.getById(userId);
        if (user.getLastNode().getClass() != Question.class) {
            Node nextNode = nodeService.getById(getNextLinkedNode(user));
            user.setLastNode(nextNode);
            userService.update(user);
            addHistory(user, nextNode);
            push(nextNode);
        }
    }

    private long getNextLinkedNode(User user) throws GoingThrowQuestionWIithoutAnswerException, FinishException {
        Node node = user.getLastNode();
        Node nextNode;
        if (node == null) {
            throw new FinishException();
        } else if (node.getClass() == Fork.class) {
            Branch branch = chooseBranch(((Fork) node).getBranches(), user.getStates());
            nextNode = branch.getNextNode();
        } else if (node.getClass() == Message.class) {
            nextNode = ((LinkedNode) node).getNextNode();
        } else {
            throw new NodeTypeException(node.getClass());
        }
        if (nextNode.getClass() == Fork.class) {
            return getNextLinkedNode(user);
        }
        return nextNode.getId();
    }

    private Branch chooseBranch(List<Branch> branchList, List<State> stateList) {
        return branchList.parallelStream()
                .filter(branch -> checkLimits(stateList, branch.getLimits()))
                .findAny().orElseThrow(ModelNotFoundException::new);
    }

    private boolean checkLimits(List<State> stateList, List<Limit> limitList) {
        for (Limit limit : limitList) {
            State state = stateList.parallelStream()
                    .filter(stateItem -> limit.getVar().equals(stateItem.getVar()))
                    .findAny().orElseThrow(ModelNotFoundException::new);
            int val = state.getVal();
            if (
                    val < limit.getMin() ||
                            val > limit.getMax() ||
                            val != limit.getEqual()) {
                return false;
            }
        }
        return true;
    }
}
