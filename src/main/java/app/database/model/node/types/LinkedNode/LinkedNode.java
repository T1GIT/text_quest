package app.database.model.node.types.LinkedNode;

import app.database.model.node.Node;
import app.database.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "linked_nodes")
@Inheritance(strategy = InheritanceType.JOINED)
public class LinkedNode extends Node {
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "next_node_id")
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
