package app.text_quest.database.model;

import app.text_quest.database.util.AuditModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "vars")
public class Var extends AuditModel {

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    public Var() {
    }

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
