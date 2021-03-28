package app.text_quest.database.model;

import app.text_quest.database.util.AuditModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 <h2> A class {@link Var}

 <p> It's object-oriented representation for table <u>vars</u>

 <p> <b> Storages: </b>
 <p> Variables' names
 <p> <b> Logic: </b>
 <p> When user make a {@link Answer choice} game changes the {@link State state}
 of {@link Change any} variables. So, all available variables are in this table.
 */
@Entity
@Table(name = "vars")
public class Var extends AuditModel {

    /**
     The name of the variable.
     <p> <b> Constraints: </b>
     <ul>
     <li> required
     <li> unique
     */
    @Column(nullable = false, unique = true)
    private String name;

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
