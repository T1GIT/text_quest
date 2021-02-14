package app.text_quest.controller;


import app.text_quest.model.User;
import app.text_quest.service.UserService;
import app.text_quest.util.modelFactory.types.PswFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getUsers(Model model) {
        List<User> userList = userService.getAll();
        model.addAttribute("names", new ArrayList<String>());
        model.addAttribute("emails", new ArrayList<String>());
        for (User user: userList) {
            ((ArrayList<String>) model.getAttribute("names")).add(user.getName());
            ((ArrayList<String>) model.getAttribute("emails")).add(user.getEmail());
        }
        return model.toString();
    }

    @PutMapping("/user/{name}/{email}")
    public User updateAnswer(@PathVariable String name,
                               @PathVariable String email) {
        User user = new User();
        user.setPsw(new PswFactory().create());
        user.setEmail(email);
        user.setName(name);
        return userService.addUser(user);
    }
}
