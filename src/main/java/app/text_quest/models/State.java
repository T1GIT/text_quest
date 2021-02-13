package app.text_quest.models;

import app.text_quest.utils.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "states")
public class State extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column
    private int val;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "var_id", nullable = false)
    @JsonIgnore
    private Var var;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    @JsonIgnore
    private User user;

    public State() { }

    public int getId() {
        return id;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Var getVar() {
        return var;
    }

    public User getUser() {
        return user;
    }

    public void setVar(Var var) {
        this.var = var;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
