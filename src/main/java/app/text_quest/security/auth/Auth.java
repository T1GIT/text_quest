package app.text_quest.security.auth;

import app.text_quest.database.model.user.User;


/**
 <h2> An interface {@link Auth}

 <p> Intended for holding authorisation in the context
 of the each request.

 <p> Implemented class contains threadLocal attribute with an {@link User} object.
 {@link app.text_quest.controller.util.filter.SecurityFilter} checks JWT
 token in the request cookie and fills auth.
 In the controller attribute of this type storage the {@link User} object in the
 current request context.
 */
public interface Auth {

    /**
     Checks if current context contains authorisation.

     @return true if request contains valid user
     */
    boolean isAuthenticated();

    /**
     Method for getting user from the current context.

     @return current user
     */
    User getUser();

    /**
     Sets user to current context.

     @param user to set
     */
    void setUser(User user);

}
