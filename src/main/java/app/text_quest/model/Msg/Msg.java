package app.text_quest.model.Msg;

import app.text_quest.util.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "msgs")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Msg extends AuditModel {

    public Msg() { }

    @Override
    public String toString() {
        return super.toString();
    }
}
