package app.database.model.node;

import app.database.model.node.types.LinkedNode.types.Message;
import app.database.model.user.User;
import app.database.util.AuditModel;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


/**
 * Object-oriented representation for table <u>nodes</u>
 * <p>
 * Base entity for specified types of nodes, using JOINED strategy for an inheritance
 * <p>
 * <b>Storages:</b>
 * Points of game
 * <p>
 * <b>Logic:</b>
 * When {@link User user} receives {@link Message message} it moves to the another {@link Node node}.
 */
@Entity
@Table(name = "nodes")
@Inheritance(strategy = InheritanceType.JOINED)
public class Node extends AuditModel {

    @Override
    public String toString() {
        return super.toString();
    }
}
