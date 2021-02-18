package app.text_quest.model;

import app.text_quest.util.AbstractModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "vars")
public class Var extends AbstractModel {
    
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
