package app.database.model.user.types;

import app.database.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Object-oriented representation for table <u>oauth_users</u>
 * <p>
 * <b>Storages:</b>
 * Creditionals of users, authorised via oauth services
 */
@Entity
@Table(name = "oauth_users")
public class OauthUser extends User {

    /**
     * Unique identifier received from the oauth service
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * <li> constant
     * <li> unique
     * </ul>
     */
    @Column(nullable = false, unique = true, updatable = false)
    private String oauthId;

    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    @Override
    public String toString() {
        return "OauthUser{" +
                "oauthId='" + oauthId + '\'' +
                '}';
    }
}
