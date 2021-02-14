package app.text_quest.model.Node;

import app.text_quest.util.AbstractEntity;
import app.text_quest.util.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "nodes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Node extends AbstractEntity {

    public Node() { }

    @Override
    public String toString() {
        return super.toString();
    }
}
