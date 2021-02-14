package app.text_quest.model;

import app.text_quest.model.Node.Node;
import app.text_quest.model.Node.types.Fork;
import app.text_quest.util.AbstractEntity;
import app.text_quest.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "branches")
public class Branch extends AbstractEntity {

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Limit> limits = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "forks_id", nullable = false)
    @JsonIgnore
    private Fork fork;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nodes_id", nullable = false)
    @JsonIgnore
    private Node nextNode;

    public Branch() { }

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
