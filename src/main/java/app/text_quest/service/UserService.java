package app.text_quest.service;

import app.text_quest.model.User;

import java.util.List;

public interface UserService {

    User addUser(User user);
    void delete(User user);
    User getByEmail(String email);
    User editUser(User user);
    List<User> getAll();
}
