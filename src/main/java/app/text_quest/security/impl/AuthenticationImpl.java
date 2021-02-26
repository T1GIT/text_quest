package app.text_quest.security.impl;

import app.text_quest.security.Authentication;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationImpl implements Authentication {

    private static final ThreadLocal<Integer> threadLocalUser = new ThreadLocal<Integer>();

    public boolean isAuthenticated() {
        return threadLocalUser.get() != null;
    }

    public Integer getUser() {
        return threadLocalUser.get();
    }

    public void setUser(Integer user) {
        AuthenticationImpl.threadLocalUser.set(user);
    }
}
