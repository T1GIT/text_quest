package app.text_quest.database.model;

import app.text_quest.database.model.user.User;
import app.text_quest.database.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * It's object-oriented representation for table <u>admins</u>
 *
 * <b>Storages:</b>
 * List of administrators
 */
@Entity
@Table(name = "admins")
public class Admin extends AuditModel {

    /**
     * User model that is administrator
     * <p>
     * <b> Constraints: </b>
     * <ul>
     * <li> required
     * <li> constant
     * </ul>
     */
    @JsonIgnore
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
