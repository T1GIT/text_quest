package app.text_quest.model;

import app.text_quest.util.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(nullable = false, length = 50)
    private String email;

    @Column(length = 50)
    private String name;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, optional = false, fetch = FetchType.LAZY)
    private Psw psw;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<State> states = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<History> histories = new ArrayList<>();

    public User() { }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Psw getPsw() {
        return psw;
    }

    public List<State> getStates() {
        return states;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPsw(Psw psw) {
        psw.setUser(this);
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
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", psw=" + psw +
                ", states=" + states +
                ", histories=" + histories +
                '}';
    }
}
