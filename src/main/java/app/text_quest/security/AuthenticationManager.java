package app.text_quest.security;

import app.text_quest.database.model.user.User;


@Deprecated
public interface AuthenticationManager<T extends User> {

    boolean exist(String uniqueIdentifier);

    T register(T user);

    T login(T user);

}
