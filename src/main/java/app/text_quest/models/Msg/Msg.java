package app.text_quest.models.Msg;

import app.text_quest.utils.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "msg")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Msg extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Msg() { }

    public int getId() {
        return id;
    }
}
