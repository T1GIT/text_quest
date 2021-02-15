package app.text_quest.model.node;

import app.text_quest.util.AbstractModel;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "nodes")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Node extends AbstractModel {

    public Node() { }

    @Override
    public String toString() {
        return super.toString();
    }
}
