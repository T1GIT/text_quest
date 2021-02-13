package app.text_quest.models.Node.types.LinkedNode;

import app.text_quest.models.Node.Node;

import javax.persistence.*;

@Entity
@Table(name = "lnd_nodes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class LinkedNode extends Node {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="nodes_id")
    private Node node;

    public LinkedNode() { }

    public Node getNode() {
        return node;
    }

    public void setNode(Node next) {
        this.node = next;
    }

    @Override
    public String toString() {
        return "LndNode{" +
                "next=" + node +
                '}';
    }
}
