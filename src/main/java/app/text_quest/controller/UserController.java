package app.text_quest.controller;


import app.text_quest.model.User;
import app.text_quest.service.UserService;
import app.text_quest.util.modelCreator.PswCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return model.toString();
    }

    @PutMapping("/user/{name}/{email}")
    public User updateAnswer(@PathVariable String name,
                               @PathVariable String email) {
        User user = new User();
        user.setPsw(PswCreator.create());
        user.setEmail(email);
        user.setName(name);
        return userService.addUser(user);
    }
}
