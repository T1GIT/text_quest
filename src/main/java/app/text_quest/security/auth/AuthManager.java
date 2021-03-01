package app.text_quest.security.auth;

import app.text_quest.database.model.user.User;


public interface AuthManager<T extends User> {

    T findUser(String uniqueIdentifier);

    T register(String uniqueIdentifier);

}
