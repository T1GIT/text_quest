package app.database.model;

import app.database.model.node.Node;
import app.database.model.node.types.Fork;
import app.database.model.user.User;
import app.database.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Object-oriented representation for table <u>branches</u>
 * <p>
 * <b>Storages:</b>
 * Possible transfers to another branch of the history, when user came to fork
 * <p>
 * <b>Logic:</b>
 * When {@link User user} came to the {@link Fork fork}, game checks
 * all available {@link Branch branches} with their {@link Condition limitations} and
 * takes suitable.
 */
@Entity
@Table(name = "branches")
public class Branch extends AuditModel {

    /**
     * All required limits
     */
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private final List<Condition> conditions = new ArrayList<>();

    /**
     * Owner fork
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "forks_id", nullable = false)
    @JsonIgnore
    private Fork fork;

    /**
     * Goal of the redirect
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nodes_id", nullable = false)
    @JsonIgnore
    private Node nextNode;

    public List<Condition> getLimits() {
        return conditions;
    }

    public Fork getFork() {
        return fork;
    }

    public void setFork(Fork fork) {
        this.fork = fork;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public void addLimit(Condition condition) {
        this.conditions.add(condition);
        condition.setBranch(this);
    }

    public void removeLimit(Condition condition) {
        this.conditions.remove(condition);
        condition.setBranch(null);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "limits=" + conditions +
                '}';
    }
}

