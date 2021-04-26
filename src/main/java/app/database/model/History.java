package app.database.model;

import app.database.model.node.Node;
import app.database.model.node.types.LinkedNode.LinkedNode;
import app.database.model.user.User;
import app.database.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


/**
 * Object-oriented representation for table <u>histories</u>
 * <p>
 * <b>Storages:</b>
 * Messages, that user already got.
 * <p>
 * <b>Logic:</b>
 * Every time, when {@link User user} receives a {@link Node message} it saving into
 * this table.
 */
@Entity
@Table(name = "histories")
public class History extends AuditModel {

    /**
     * Received message
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> constant
     * </ul>
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "node_id", updatable = false)
    @JsonIgnore
    private LinkedNode node;

    /**
     * User, who got this message
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * <li> constant
     * </ul>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false, updatable = false)
    @JsonIgnore
    private User user;

    public LinkedNode getNode() {
        return node;
    }

    public void setNode(LinkedNode node) {
        this.node = node;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public History() {
    }

    public History(User user, LinkedNode node) {
        this.user = user;
        this.node = node;
    }

    @Override
    public String toString() {
        return "History{" +
                "node=" + node +
                ", user=" + user +
                '}';
    }
}
