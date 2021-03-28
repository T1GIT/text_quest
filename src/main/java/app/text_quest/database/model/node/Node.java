package app.text_quest.database.model.node;

import app.text_quest.database.util.AuditModel;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Table(name = "nodes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Node extends AuditModel {

    @Override
    public String toString() {
        return super.toString();
    }
}
