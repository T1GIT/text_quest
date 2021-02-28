package app.text_quest.database.service;


import app.text_quest.database.model.user.types.BasicUser;

import java.util.List;


public interface BasicUserService {

    BasicUser addBasicUser(BasicUser basicUser);

    void delete(BasicUser basicUser);

    BasicUser getByMail(String mail);

    BasicUser editBasicUser(BasicUser basicUser);

    List<BasicUser> getAll();
}
