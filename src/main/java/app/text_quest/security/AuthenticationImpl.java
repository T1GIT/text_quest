package app.text_quest.security;

import app.text_quest.database.model.User;
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
