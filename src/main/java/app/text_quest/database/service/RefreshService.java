package app.text_quest.database.service;

import app.text_quest.database.model.Refresh;
import app.text_quest.database.model.user.User;
import app.text_quest.database.util.AbstractService;

import java.util.Date;


public interface RefreshService extends AbstractService<Refresh> {// TODO: 04.03.2021 Add fingerprint. Storage with tokens

    Refresh getByUser(User user);

    Refresh getByValue(String value);

    void deleteBefore(Date date);
}
