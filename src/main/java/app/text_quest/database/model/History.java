package app.text_quest.database.model;

import app.text_quest.database.model.node.Node;
import app.text_quest.database.model.user.User;
import app.text_quest.database.util.AuditModel;
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
     * <li> required
     * <li> constant
     * </ul>
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "msg_id", nullable = false, updatable = false)
    @JsonIgnore
    private Node node;

    /**
     * User, who got this message
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

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "History{" +
                "node=" + node +
                ", user=" + user +
                '}';
    }
}
