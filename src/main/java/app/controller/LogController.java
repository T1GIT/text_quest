package app.controller;


import app.database.model.user.User;
import app.database.service.userService.UserService;
import app.database.util.enums.Role;
import app.security.auth.Auth;
import app.util.LoggerFactory;
import app.util.constant.LogType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Controller for getting logs.
 * <p>
 * <b>Using:</b>
 * Send GET-request in the template:
 * <p>
 * [host]/log/[logFileName(without extension ".log")]
 */
@Controller
@RequestMapping("/log")
public class LogController {

    private final static Logger adminLogger = LoggerFactory.getLogger(LogType.ADMIN);

    /**
     * Secret key, identifying administrator
     */
    private final Auth auth;

    private final UserService userService;

    /**
     * Class constructor specified an auth context
     *
     * @param auth for getting administrator
     * @param userService
     */
    public LogController(Auth auth, UserService userService) {
        this.auth = auth;
        this.userService = userService;
    }

    /**
     * Sends log file.
     * Checks secret key, if it is valid transfers requested log file
     *
     * @param name name of the log file
     * @return log file
     */
    @GetMapping("/{name}")
    @ResponseBody
    public String log(@PathVariable String name) {
        User user = userService.getById(auth.getUser().getId());
        if (user.getRole() != Role.ADMIN)
            return "You are not an administrator";
        adminLogger.info(String.format("admin id: %5d name: %s.log",
                user.getId(), name));
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(".log/" + name + ".log"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("<br>");
            }
        } catch (IOException e) {
            return "Can't find log file " + name;
        }
        return sb.toString();
    }
}
