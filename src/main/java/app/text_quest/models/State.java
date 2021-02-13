package app.text_quest.models;

import app.text_quest.utils.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", val=" + val +
                ", var=" + var +
                ", user=" + user +
                '}';
    }
}
