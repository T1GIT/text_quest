package app.text_quest.security.impl;

import app.text_quest.database.model.User;
import app.text_quest.security.Authorisation;
import org.springframework.stereotype.Component;


@Component
public class AuthorisationImpl implements Authorisation {

    private static final ThreadLocal<User> threadLocalUser = new ThreadLocal<User>();

    public boolean isAuthenticated() {
        return threadLocalUser.get() != null;
    }

    public User getUser() {
        return threadLocalUser.get();
    }

    public void setUser(User user) {
        AuthorisationImpl.threadLocalUser.set(user);
    }
}
