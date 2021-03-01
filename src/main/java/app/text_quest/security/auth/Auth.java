package app.text_quest.security.auth;

import app.text_quest.database.model.user.User;


public interface Auth {

    boolean isAuthenticated();

    User getUser();

    void setUser(User user);

}
