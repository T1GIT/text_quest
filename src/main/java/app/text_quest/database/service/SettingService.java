package app.text_quest.database.service;

import app.text_quest.database.model.Setting;
import app.text_quest.database.model.User;

import java.util.List;

@Deprecated
public interface SettingService {
    Setting addSetting(Setting setting);

    void delete(Setting setting);

    Setting getByUser(User user);

    Setting editSetting(Setting setting);

    List<Setting> getAll();
}
