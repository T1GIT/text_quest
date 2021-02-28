package app.text_quest.security;

import app.text_quest.database.model.user.types.OauthUser;


@Deprecated
public abstract class OauthAuthenticationManager<T> implements AuthenticationManager<OauthUser> {

    @Override
    public abstract boolean exist(String oauthId);

    @Override
    public abstract OauthUser register(OauthUser user);

    @Override
    public abstract OauthUser login(OauthUser user);
}
