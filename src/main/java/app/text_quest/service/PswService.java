package app.text_quest.service;

import app.text_quest.model.Psw;
import app.text_quest.model.User;

import java.util.List;

@Deprecated
public interface PswService {
    Psw addPsw(Psw psw);
    void delete(Psw psw);
    Psw getByUser(User user);
    Psw editPsw(Psw psw);
    List<Psw> getAll();
}
