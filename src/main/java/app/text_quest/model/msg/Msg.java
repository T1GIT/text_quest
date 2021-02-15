package app.text_quest.model.msg;

import app.text_quest.util.AbstractModel;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "msgs")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Msg extends AbstractModel {

    public Msg() { }

    @Override
    public String toString() {
        return super.toString();
    }
}
