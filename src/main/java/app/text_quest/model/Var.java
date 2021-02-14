package app.text_quest.model;

import app.text_quest.util.AbstractEntity;
import app.text_quest.util.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "vars")
public class Var extends AbstractEntity {
    
    @Column(nullable = false, length = 30, unique = true)
    private String name;

    public Var() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Var{" +
                "name='" + name + '\'' +
                '}';
    }
}
