package app.text_quest.models;

import app.text_quest.models.Node.Node;
import app.text_quest.models.Node.types.Fork;
import app.text_quest.utils.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "branches")
public class Branch extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Limit> limits;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "forks_id", nullable = false)
    @JsonIgnore
    private Fork fork;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nodes_id", nullable = false)
    @JsonIgnore
    private Node next;

    public Branch() { }

    public int getId() {
        return id;
    }

    public List<Limit> getReqs() {
        return limits;
    }

    public Fork getFork() {
        return fork;
    }

    public Node getNext() {
        return next;
    }

    public void setFork(Fork fork) {
        this.fork = fork;
    }

    public void setNext(Node next) {
        this.next = next;
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
                "id=" + id +
                ", reqs=" + limits +
                ", fork=" + fork +
                ", next=" + next +
                '}';
    }
}
