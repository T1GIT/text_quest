package app.text_quest.model.node;

import app.text_quest.util.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "nodes")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Node extends AbstractEntity {

    public Node() { }

    @Override
    public String toString() {
        return super.toString();
    }
}
