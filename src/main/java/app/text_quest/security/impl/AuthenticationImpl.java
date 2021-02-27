package app.text_quest.security.impl;

import app.text_quest.database.model.user.User;
import app.text_quest.security.Authentication;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationImpl implements Authentication {

    private static final ThreadLocal<User> threadLocalUser = new ThreadLocal<User>();

    public boolean isAuthenticated() {
        return threadLocalUser.get() != null;
    }

    public User getUser() {
        return threadLocalUser.get();
    }

    public void setUser(User user) {
        AuthenticationImpl.threadLocalUser.set(user);
    }
}
