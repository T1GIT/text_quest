package app.text_quest.database.service;

import app.text_quest.database.model.Psw;
import app.text_quest.database.model.User;

import java.util.List;

@Deprecated
public interface PswService {
    Psw addPsw(Psw psw);
    void delete(Psw psw);
    Psw getByUser(User user);
    Psw editPsw(Psw psw);
    List<Psw> getAll();
}
