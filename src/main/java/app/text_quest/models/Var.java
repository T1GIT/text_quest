package app.text_quest.models;

import app.text_quest.utils.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "var")
public class Var extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(nullable = false, length = 30, unique = true)
    private String name;

    public Var() { }

    public int getId() {
        return id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
