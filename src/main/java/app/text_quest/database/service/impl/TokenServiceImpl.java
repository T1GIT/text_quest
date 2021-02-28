package app.text_quest.database.service.impl;

import app.text_quest.database.model.Token;
import app.text_quest.database.model.user.User;
import app.text_quest.database.repo.TokenRepository;
import app.text_quest.database.service.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Deprecated
@Service
@Transactional
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token addToken(Token token) {
        return this.tokenRepository.saveAndFlush(token);
    }

    @Override
    public void delete(Token token) {
        this.tokenRepository.delete(token);
    }

    @Override
    public Token getByUser(User user) {
        return this.tokenRepository.findByUserId(user.getId());
    }

    @Override
    public void deleteBefore(Date date) {
        this.tokenRepository.deleteAllByCreatedAtBefore(date);
    }

    @Override
    public List<Token> getAll() {
        return this.tokenRepository.findAll();
    }
}
