package app.text_quest.database.model;

import app.text_quest.database.model.node.Node;
import app.text_quest.database.model.node.types.Fork;
import app.text_quest.database.model.user.User;
import app.text_quest.database.util.AuditModel;
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
 * all available {@link Branch branches} with their {@link Limit limitations} and
 * takes suitable.
 */
@Entity
@Table(name = "branches")
public class Branch extends AuditModel {

    /**
     * All required limits
     */
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Limit> limits = new ArrayList<>();

    /**
     * Owner fork
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "forks_id", nullable = false)
    @JsonIgnore
    private Fork fork;

    /**
     * Goal of the redirect
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nodes_id", nullable = false)
    @JsonIgnore
    private Node nextNode;

    public List<Limit> getLimits() {
        return limits;
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

    public void addLimit(Limit limit) {
        this.limits.add(limit);
        limit.setBranch(this);
    }

    public void removeLimit(Limit limit) {
        this.limits.remove(limit);
        limit.setBranch(null);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "limits=" + limits +
                ", fork=" + fork +
                ", nextNode=" + nextNode +
                '}';
    }
}
