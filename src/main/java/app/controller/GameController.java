package app.controller;


import app.controller.util.exception.game.types.FinishException;
import app.core.NotifyTimer;
import app.core.Tree;
import app.controller.util.constant.Period;
import app.controller.util.exception.game.types.ModelNotFoundException;
import app.controller.util.json.game.InitialResponse;
import app.database.model.*;
import app.database.model.node.types.LinkedNode.LinkedNode;
import app.database.model.node.types.LinkedNode.types.Message;
import app.database.model.node.types.LinkedNode.types.Question;
import app.database.model.user.User;
import app.database.service.HistoryService;
import app.database.service.NodeService;
import app.database.service.StateService;
import app.database.service.userService.UserService;
import app.security.auth.Auth;
import app.security.secretFactory.types.SocketIdFactory;
import app.util.LoggerFactory;
import app.util.constant.LogType;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// TODO: Document
@RestController
@RequestMapping("/game")
public class GameController {

    private final static Logger errLogger = LoggerFactory.getLogger(LogType.ERROR);
    private final static SocketIdFactory socketIdFactory = new SocketIdFactory();
    private final static Gson gson = new Gson();

    private final SimpMessagingTemplate messagingTemplate;
    private final Tree tree;
    private final Auth auth;
    private final UserService userService;
    private final NodeService nodeService;
    private final HistoryService historyService;

    public GameController(
            SimpMessagingTemplate messagingTemplate,
            Tree tree, Auth auth,
            UserService userService, NodeService nodeService, HistoryService historyService) {
        this.messagingTemplate = messagingTemplate;
        this.tree = tree;
        this.auth = auth;
        this.userService = userService;
        this.nodeService = nodeService;
        this.historyService = historyService;
    }

    @GetMapping(value = "/start", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public InitialResponse start() {
        InitialResponse response = new InitialResponse();
        String socketId = socketIdFactory.create();
        User user = userService.getById(auth.getUser().getId());
        user.setSocketId(socketId);
        userService.update(user);
        response.setSocketId(socketId);
        LinkedNode lastNode = historyService.getLast(user).getNode();
        if (lastNode.getClass() == Message.class) {
            NotifyTimer.start(user, messagingTemplate, tree, nodeService, historyService);
        }
        response.setLastNode(lastNode);
        return response;
    }

    @PostMapping("/stop")
    public final void stop() {

        NotifyTimer.stop(auth.getUser());
    }

    @GetMapping(value = "/message", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Message sendMessage() {
        return (Message) historyService.getLast(auth.getUser()).getNode();
    }

    @GetMapping(value = "/question", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Question sendQuestion() {
        return (Question) historyService.getLast(auth.getUser()).getNode();
    }

    @Transactional
    @PostMapping(value="/answer", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public void receiveAnswer(@RequestBody Answer requestAnswer) {
        User user = userService.getById(auth.getUser().getId());
        try {
            Question question = (Question) historyService.getLast(user).getNode();
            Answer answer = question.getAnswers().parallelStream()
                    .filter(answerItem -> answerItem.getId() == requestAnswer.getId())
                    .findAny().orElseThrow(ModelNotFoundException::new);
            user.addAnswer(answer);
            List<State> stateList = user.getStates();
            for (Change change : answer.getChanges()) {
                State state = stateList.parallelStream()
                        .filter(stateItem -> change.getVar().getId() == stateItem.getVar().getId())
                        .findAny().orElseThrow(ModelNotFoundException::new);
                state.setVal(state.getVal() + change.getVal());
            }
            userService.update(user);
            LinkedNode nextNode = tree.nextNode(question, user);
            historyService.add(new History(user, nextNode));
            NotifyTimer.start(user, messagingTemplate, tree, nodeService, historyService);
        } catch (ClassCastException | ModelNotFoundException e) {
            errLogger.error(e.getMessage(), e);
        } catch (FinishException e) {
            historyService.add(new History(user, null));
        }
    }
}
