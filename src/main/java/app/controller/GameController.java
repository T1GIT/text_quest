package app.controller;


import app.database.service.NodeService;
import app.database.service.userService.UserService;
import app.util.LoggerFactory;
import app.util.constant.LogType;
import org.apache.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// TODO: Document
@Controller
@RequestMapping("/game")
public class GameController {

    private final Logger logger = LoggerFactory.getLogger(LogType.ERROR);

    private SimpMessagingTemplate messagingTemplate;
    private UserService userService;
    private NodeService nodeService;

    @MessageMapping("/test_in")
    public void processMessage(@Payload String message) {
        messagingTemplate.convertAndSend("/test_out", "Answer");
    }
}
