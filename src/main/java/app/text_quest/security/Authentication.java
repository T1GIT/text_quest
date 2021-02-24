package app.text_quest.security;

import app.text_quest.database.model.User;


public interface Authentication {

    boolean isAuthenticated();

    User getUser();

    void setUser(User user);

}
