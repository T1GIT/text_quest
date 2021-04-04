package app.controller;


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
import java.util.HashSet;

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
     * Collection storing ids of administrators' accounts,
     * having access to receiving log files.
     */
    private final HashSet<Long> admins;

    /**
     * Secret key, identifying administrator
     */
    private final Auth auth;

    /**
     * Class constructor specified an auth context
     *
     * @param auth for getting administrator
     */
    public LogController(Auth auth) {
        this.auth = auth;
        this.admins = new HashSet<>();
        admins.add(2930582334L);
        admins.add(3940340340L);
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
        if (admins.contains(auth.getUser().getId()))
            return "You are not an administrator";
        adminLogger.info(auth.getUser().getId() + ":" + name + ".log");
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
