package app.text_quest.database.service;

import app.text_quest.database.model.user.types.OauthUser;

import java.util.List;


public interface OauthUserService {

    OauthUser addOauthUser(OauthUser oauthUser);

    void delete(OauthUser oauthUser);

    OauthUser getByOauthId(String oauthId);

    OauthUser editOauthUser(OauthUser oauthUser);

    List<OauthUser> getAll();
}
