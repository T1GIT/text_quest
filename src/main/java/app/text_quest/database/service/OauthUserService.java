package app.text_quest.database.service;

import app.text_quest.database.model.user.types.OauthUser;
import app.text_quest.database.util.AbstractService;


public interface OauthUserService extends AbstractService<OauthUser> {

    OauthUser getByOauthId(String oauthId);
}
