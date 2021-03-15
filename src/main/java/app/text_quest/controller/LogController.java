package app.text_quest.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Controller
@RequestMapping("/")
public class LogController {

    private final static String KEY = "LO2Og8wLcPkxlSoi8WtQM62ROMRlExawhOgjTSEf74uD5Mejxf";

    @GetMapping("log/{name}")
    @ResponseBody
    public String log(@PathVariable String name, HttpServletRequest req) throws IOException {
        if (!req.getParameter("key").equals(KEY))
            return "Invalid key";
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(".log/" + name + ".log"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            return "Can't find log file " + name;
        }
        return sb.toString();
    }
}
