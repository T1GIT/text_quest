package app.text_quest.database.model.node.types.LinkedNode;

import app.text_quest.database.model.node.Node;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
//@Table(name = "lnd_nodes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class LinkedNode extends Node {
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "nodes_id")
    private Node nextNode;

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
