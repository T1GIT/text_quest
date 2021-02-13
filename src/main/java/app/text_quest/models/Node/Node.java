package app.text_quest.models.Node;

import app.text_quest.utils.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "nodes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Node extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Node() { }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                '}';
    }
}
