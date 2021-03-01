package app.text_quest.security.auth;

import app.text_quest.database.model.user.types.OauthUser;


public interface OauthAuthManager extends AuthManager<OauthUser> {

    @Override
    OauthUser findUser(String oauthId);

    @Override
    OauthUser register(String oauthId);

}
