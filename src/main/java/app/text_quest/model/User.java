package app.text_quest.model;

import app.text_quest.util.AbstractModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends AbstractModel {

    @Column(nullable = false, length = 50)
    private String email;

    @Column(length = 50)
    private String name;

    private boolean google;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, optional = false, fetch = FetchType.LAZY)
    private Psw psw;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, optional = false, fetch = FetchType.LAZY)
    private Setting setting;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<State> states = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<History> histories = new ArrayList<>();

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

    public Setting getSetting() {
        return setting;
    }

    public List<State> getStates() {
        return states;
    }

    public List<History> getHistories() {
        return histories;
    }

    public boolean isGoogle() {
        return google;
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

    public void setSetting(Setting setting) {
        setting.setUser(this);
        this.setting = setting;
    }

    public void setGoogle(boolean google) {
        this.google = google;
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
                ", setting=" + setting +
                ", states=" + states +
                ", histories=" + histories +
                '}';
    }
}
