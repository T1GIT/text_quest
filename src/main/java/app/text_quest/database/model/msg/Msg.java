package app.text_quest.database.model.msg;

import app.text_quest.database.util.AuditModel;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Table(name = "msgs")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Msg extends AuditModel {

    public Msg() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
