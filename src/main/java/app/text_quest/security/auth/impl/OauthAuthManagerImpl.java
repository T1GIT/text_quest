package app.text_quest.security.auth.impl;

import app.text_quest.database.model.Setting;
import app.text_quest.database.model.Token;
import app.text_quest.database.model.user.types.OauthUser;
import app.text_quest.database.service.OauthUserService;
import app.text_quest.security.auth.OauthAuthManager;
import app.text_quest.security.util.secretFactory.types.TokenFactory;
import org.springframework.stereotype.Component;


@Component
public class OauthAuthManagerImpl implements OauthAuthManager {

    private final static TokenFactory tokenFactory = new TokenFactory();
    private final OauthUserService service;

    public OauthAuthManagerImpl(OauthUserService service) {
        this.service = service;
    }


    @Override
    public OauthUser findUser(String oauthId) {
        return service.getByOauthId(oauthId);
    }

    @Override
    public OauthUser register(String oauthId) {
        OauthUser user = new OauthUser();
        user.setOauthId(oauthId);
        Setting setting = new Setting();
        Token token = new Token();
        token.setValue(tokenFactory.create());
        user.setSetting(setting);
        user.addToken(token);
        service.addOauthUser(user);
        return user;
    }

}
