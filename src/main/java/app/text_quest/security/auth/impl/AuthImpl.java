package app.text_quest.security.auth.impl;

import app.text_quest.database.model.user.User;
import app.text_quest.security.auth.Auth;
import org.springframework.stereotype.Component;


@Component
public class AuthImpl implements Auth {

    private static final ThreadLocal<User> threadLocalUser = new ThreadLocal<User>();

    public boolean isAuthenticated() {
        return threadLocalUser.get() != null;
    }

    public User getUser() {
        return threadLocalUser.get();
    }

    public void setUser(User user) {
        AuthImpl.threadLocalUser.set(user);
    }
}
