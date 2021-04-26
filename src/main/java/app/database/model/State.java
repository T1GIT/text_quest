package app.database.model;

import app.database.model.user.User;
import app.database.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


/**
 * Object-oriented representation for table <u>states</u>
 * <p>
 * <b>Storages:</b>
 * Values of variables for each user
 * <p>
 * <b>Logic:</b>
 * When {@link User user}  make a {@link Answer choice} game changes the {@link State state}
 * of {@link Change any} variables. So, all available variables are in this table.
 */
@Entity
@Table(name = "states")
public class State extends AuditModel {

    @Column(nullable = false)
    private int val = 0;

    /**
     * The variable model.
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * <li> constant
     * <li> unique
     * <li> length {@literal <} 31
     * </ul>
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "var_id", nullable = false, updatable = false)
    @JsonIgnore
    private Var var;

    /**
     * The user model.
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * <li> constant
     * </ul>
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id", nullable = false, updatable = false)
    @JsonIgnore
    private User user;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Var getVar() {
        return var;
    }

    public void setVar(Var var) {
        this.var = var;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "State{" +
                "val=" + val +
                ", var=" + var +
                '}';
    }
}
