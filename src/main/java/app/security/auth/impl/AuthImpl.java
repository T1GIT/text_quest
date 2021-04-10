package app.security.auth.impl;

import app.database.model.user.User;
import app.security.auth.Auth;
import org.springframework.stereotype.Component;


/**
 * @see app.security.auth.Auth
 */
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
