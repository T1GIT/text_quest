package app.text_quest.models.Node.types.LndNode;

import app.text_quest.models.Node.Node;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "lnd_nodes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class LndNode extends Node {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="nodes_id")
    private Node next;

    public LndNode() { }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "LndNode{" +
                "next=" + next +
                '}';
    }
}
