package app.text_quest.database.util.modelFactory.types;

import app.text_quest.database.model.Setting;
import app.text_quest.database.model.user.User;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;


/**
 * @see app.text_quest.database.util.modelFactory.AbstractModelFactory
 */
public class UserFactory extends AbstractModelFactory<User> {
    private final static SettingFactory settingFactory = new SettingFactory();
    private final String name = "user_name";
    private final Setting setting = settingFactory.create();

    @Override
    public User create() {
        User user = new User();
        user.setName(this.name);
        user.setSetting(settingFactory.create());
        return user;
    }

    public String getName() {
        return name;
    }

    public Setting getSetting() {
        return setting;
    }
}
