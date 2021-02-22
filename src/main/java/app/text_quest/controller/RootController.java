package app.text_quest.controller;


import app.text_quest.util.ExceptionLogger;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;


@Controller
public class RootController {

    private final Logger logger = Logger.getLogger("controllerLogger");

    @GetMapping("/")
    @ResponseBody
    public String root() {
        StringBuilder res = new StringBuilder();
        try {
            String path = "classpath:/";
            File dir = new File(path);
            for (File file1 : dir.listFiles()) {
                res.append(file1);
                if (file1.isDirectory() && file1.listFiles() != null) {
                    for (File file2 : file1.listFiles()) {
                        res.append(file2);
                        if (file2.isDirectory() && file2.listFiles() != null) {
                            for (File file3 : file2.listFiles()) {
                                res.append(file3);
                            }
                        }
                    }
                }
            }
            return res.toString();
        } catch (Exception e) {
            logger.error(ExceptionLogger.getExceptionLog(e));
            return res.toString();
        }
//        return null; // TODO: add error page
    }
}
