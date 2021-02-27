package app.text_quest.database.util.modelFactory.types;

import app.text_quest.database.model.Psw;
import app.text_quest.database.model.Setting;
import app.text_quest.database.model.User;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;

public class UserFactory extends AbstractModelFactory<User> {
    private final static PswFactory pswFactory = new PswFactory();
    private final static SettingFactory settingFactory = new SettingFactory();
    private final String email = "test_email@mail.ru";
    private final String name = "user_name";
    private final Psw psw = pswFactory.create();
    private final Setting setting = settingFactory.create();

    @Override
    public User create() {
        User user = new User();
        user.setEmail(this.email);
        user.setName(this.name);
        user.setPsw(pswFactory.create());
        user.setSetting(settingFactory.create());
        return user;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Psw getPsw() {
        return psw;
    }

    public Setting getSetting() {
        return setting;
    }
}
