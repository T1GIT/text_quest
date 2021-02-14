package app.text_quest.model;

import app.text_quest.util.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "vars")
public class Var extends AuditModel {
    
    @Column(nullable = false, length = 30, unique = true)
    private String name;

    public Var() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    to
}
