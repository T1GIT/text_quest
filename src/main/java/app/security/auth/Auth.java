package app.security.auth;

import app.controller.filter.SecurityFilter;
import app.database.model.user.User;


/**
 * Holds authorisation in the context of the each request.
 * <p>
 * Implemented class contains threadLocal attribute with an {@link User} object.
 * {@link SecurityFilter} checks JWT
 * token in the request cookie and fills auth.
 * <p>
 * In the controller attribute of this type storage the {@link User} object in the
 * current request context.
 */
public interface Auth {

    /**
     * Checks if current context contains authorisation.
     *
     * @return true if request contains valid user
     */
    boolean isAuthenticated();

    /**
     * Method for getting user from the current context.
     *
     * @return current user
     */
    User getUser();

    /**
     * Sets user to current context.
     *
     * @param user to set
     */
    void setUser(User user);
}
