package app.text_quest.models;

import app.text_quest.utils.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 50)
    private String name;

    @JsonIgnore
    @OneToOne(mappedBy = "user", orphanRemoval = true, optional = false)
    private Psw psw;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<State> states;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<History> histories;

    public User() { }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<History> getHists() {
        return histories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Psw getPsw() {
        return psw;
    }

    public List<State> getStates() {
        return states;
    }

    public void setPsw(Psw psw) {
        this.psw = psw;
    }

    public void addHistory(History history) {
        this.histories.add(history);
        history.setUser(this);
    }

    public void removeHistory(History history) {
        this.histories.remove(history);
        history.setUser(null);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", psw=" + psw +
                ", states=" + states +
                ", hists=" + histories +
                '}';
    }
}
