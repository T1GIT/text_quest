package app.controller;


import app.controller.util.NotifyTimer;
import app.controller.util.exception.game.types.ModelNotFoundException;
import app.controller.util.json.game.InitialResponse;
import app.database.model.*;
import app.database.model.node.Node;
import app.database.model.node.types.LinkedNode.types.Message;
import app.database.model.node.types.LinkedNode.types.Question;
import app.database.model.user.User;
import app.database.service.HistoryService;
import app.database.service.NodeService;
import app.database.service.userService.UserService;
import app.security.auth.Auth;
import app.security.secretFactory.types.SocketIdFactory;
import app.util.LoggerFactory;
import app.util.constant.LogType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

// TODO: Document
@RestController
@RequestMapping("/game")
public class GameController {

    private final static Logger errLogger = LoggerFactory.getLogger(LogType.ERROR);
    private final static SocketIdFactory socketIdFactory = new SocketIdFactory();
    private final static Gson gson = new Gson();

    private final SimpMessagingTemplate messagingTemplate;
    private final Auth auth;
    private final UserService userService;
    private final NodeService nodeService;
    private final HistoryService historyService;

    public GameController(
            SimpMessagingTemplate messagingTemplate,
            Auth auth,
            UserService userService,
            NodeService nodeService, SimpUserRegistry userRegistry, HistoryService historyService) {
        this.messagingTemplate = messagingTemplate;
        this.auth = auth;
        this.userService = userService;
        this.nodeService = nodeService;
        this.historyService = historyService;
    }

    @GetMapping(value = "/start", produces = "application/json")
    public InitialResponse start() {
        User user = userService.getById(auth.getUser().getId());
        String socketId = socketIdFactory.create();
        user.setSocketId(socketId);
        userService.update(user);
        TimerTask task = new NotifyTimer(socketId, user.getId(), messagingTemplate,
                userService, nodeService, historyService);
        new Timer().schedule(task, 1000);
        InitialResponse response = new InitialResponse();
        response.setSocketId(socketId);
        response.setLastNode(user.getLastNode());
        return response;
    }

    @GetMapping(value = "/message", produces = "application/json")
    public Message sendMessage() {
        return (Message) userService.getById(auth.getUser().getId()).getLastNode();
    }

    @GetMapping(value = "/question", produces = "application/json")
    public Question sendQuestion() {
        return (Question) userService.getById(auth.getUser().getId()).getLastNode();
    }

    @MessageMapping(value="/answer")
    public void receiveAnswer(@Payload Answer requestAnswer) {
        User user = auth.getUser();
        try {
            Question question = (Question) user.getLastNode();
            Answer answer = question.getAnswers().parallelStream()
                    .filter(answerItem -> answerItem.getId() == requestAnswer.getId())
                    .findAny().orElseThrow(ModelNotFoundException::new);
            List<State> stateList = user.getStates();
            for (Change change : answer.getChanges()) {
                State state = stateList.parallelStream()
                        .filter(stateItem -> change.getVar().equals(stateItem.getVar()))
                        .findAny().orElseThrow(ModelNotFoundException::new);
                state.setVal(state.getVal() + change.getVal());
            }
            addHistory(user, question.getNextNode());
            user.addAnswer(answer);
            userService.update(user);
            TimerTask task = new NotifyTimer(user.getSocketId(), user.getId(),
                    messagingTemplate, userService, nodeService, historyService);
            new Timer().schedule(task, 1000);
        } catch (ClassCastException | ModelNotFoundException e) {
            errLogger.error(e.getMessage(), e);
        }
    }

    private void addHistory(User user, Node nextNode) {
        History history = new History();
        history.setNode(nextNode);
        history.setUser(user);
        historyService.add(history);
    }
}
