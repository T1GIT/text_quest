package app.database.model.node.types.LinkedNode;

import app.database.model.node.Node;
import app.database.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Object-oriented representation for table <u>lnd_nodes</u>
 * <p>
 * Base entity for income and outcome messages.
 * <p>
 * <b>Storages:</b>
 * Nodes having next node link
 * <p>
 * <b>Logic:</b>
 * When {@link User user} transferred into this {@link Node node}, it will be redirected to the next {@link Node node}
 * specified in the {@link LinkedNode#nextNode}.
 */
@Entity
@Table(name = "lnd_nodes")
@Inheritance(strategy = InheritanceType.JOINED)
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
