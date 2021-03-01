package app.text_quest.security.auth;

import app.text_quest.database.model.user.types.BasicUser;


public interface BasicAuthManager extends AuthManager<BasicUser> {

    @Override
    BasicUser findUser(String mail);

    @Override
    BasicUser register(String mail);

}
