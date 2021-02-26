package app.text_quest.security;

public interface Authentication {

    boolean isAuthenticated();

    Integer getUser();

    void setUser(Integer user);

}
