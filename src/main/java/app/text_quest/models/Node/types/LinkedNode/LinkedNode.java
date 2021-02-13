package app.text_quest.models.Node.types.LinkedNode;

import app.text_quest.models.Node.Node;

import javax.persistence.*;

@Entity
@Table(name = "lnd_nodes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class LinkedNode extends Node {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="nodes_id")
    private Node next;

    public LinkedNode() { }

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
