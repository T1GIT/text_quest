package app.text_quest.models.Node;

import app.text_quest.models.Branch;
import app.text_quest.models.Change;
import app.text_quest.models.Node.types.LndNode.LndNode;
import app.text_quest.models.Req;
import app.text_quest.utils.AuditModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nodes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Node extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Node() { }

    public int getId() {
        return id;
    }
}
