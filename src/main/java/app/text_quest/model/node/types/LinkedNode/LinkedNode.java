package app.text_quest.model.node.types.LinkedNode;

import app.text_quest.model.node.Node;

import javax.persistence.*;

@Entity
@Table(name = "lnd_nodes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class LinkedNode extends Node {
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "nodes_id")
    private Node nextNode;

    public LinkedNode() { }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return "LndNode{" +
                "nextNode=" + nextNode +
                '}';
    }
}
