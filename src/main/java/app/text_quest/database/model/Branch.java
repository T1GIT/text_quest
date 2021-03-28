package app.text_quest.database.model;

import app.text_quest.database.model.node.Node;
import app.text_quest.database.model.node.types.Fork;
import app.text_quest.database.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "branches")
public class Branch extends AuditModel {

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Limit> limits = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "forks_id", nullable = false)
    @JsonIgnore
    private Fork fork;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nodes_id", nullable = false)
    @JsonIgnore
    private Node nextNode;

    public List<Limit> getReqs() {
        return limits;
    }

    public Fork getFork() {
        return fork;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setFork(Fork fork) {
        this.fork = fork;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public void addReq(Limit limit) {
        this.limits.add(limit);
        limit.setBranch(this);
    }

    public void removeReq(Limit limit) {
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
