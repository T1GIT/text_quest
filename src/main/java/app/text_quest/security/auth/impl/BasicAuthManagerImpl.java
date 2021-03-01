package app.text_quest.security.auth.impl;

import app.text_quest.database.model.Setting;
import app.text_quest.database.model.Token;
import app.text_quest.database.model.user.types.BasicUser;
import app.text_quest.database.service.BasicUserService;
import app.text_quest.security.auth.BasicAuthManager;
import app.text_quest.security.util.secretFactory.types.TokenFactory;
import org.springframework.stereotype.Component;


@Component
public class BasicAuthManagerImpl implements BasicAuthManager {

    private final static TokenFactory tokenFactory = new TokenFactory();

    private final BasicUserService service;

    public BasicAuthManagerImpl(BasicUserService service) {
        this.service = service;
    }

    @Override
    public BasicUser findUser(String mail) {
        BasicUser user = service.getByMail(mail);
        return service.getByMail(mail);
    }

    @Override
    public BasicUser register(String mail) {
        BasicUser user = new BasicUser();
        user.setMail(mail);
        Setting setting = new Setting();
        Token token = new Token();
        token.setValue(tokenFactory.create());
        user.setSetting(setting);
        user.addToken(token);
        service.addBasicUser(user);
        return user;
    }

}
