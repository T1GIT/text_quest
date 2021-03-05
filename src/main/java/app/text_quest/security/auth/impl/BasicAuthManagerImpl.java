package app.text_quest.security.auth.impl;

import app.text_quest.database.model.Refresh;
import app.text_quest.database.model.Setting;
import app.text_quest.database.model.user.types.BasicUser;
import app.text_quest.database.service.BasicUserService;
import app.text_quest.security.auth.BasicAuthManager;
import app.text_quest.security.util.secretFactory.types.RefreshFactory;
import org.springframework.stereotype.Component;


@Deprecated
@Component
public class BasicAuthManagerImpl implements BasicAuthManager {

    private final static RefreshFactory REFRESH_FACTORY = new RefreshFactory();

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
        Refresh refresh = new Refresh();
        refresh.setValue(REFRESH_FACTORY.create());
        user.setSetting(setting);
        user.addToken(refresh);
        service.add(user);
        return user;
    }

}
