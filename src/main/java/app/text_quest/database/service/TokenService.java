package app.text_quest.database.service;

import app.text_quest.database.model.Token;
import app.text_quest.database.model.user.User;

import java.util.Date;
import java.util.List;


public interface TokenService {

    Token addToken(Token token);

    void delete(Token token);

    Token getByUser(User user);

    void deleteBefore(Date date);

    List<Token> getAll();
}
