package app.text_quest.database.service;

import app.text_quest.database.model.user.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    void delete(User user);
    User editUser(User user);
    List<User> getAll();
}
