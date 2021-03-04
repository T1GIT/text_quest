package app.text_quest.database.service;


import app.text_quest.database.model.user.types.BasicUser;
import app.text_quest.database.util.AbstractService;


public interface BasicUserService extends AbstractService<BasicUser> {

    BasicUser getByMail(String mail);
}
