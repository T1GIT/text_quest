package app.text_quest.database.service;

import app.text_quest.database.model.Token;
import app.text_quest.database.model.User;

import java.util.Date;
import java.util.List;


@Deprecated
public interface TokenService {

    Token addToken(Token token);

    void delete(Token token);

    Token getByUser(User user);

    void deleteBefore(Date date);

    List<Token> getAll();
}
