package app.text_quest.database.model.user;

import app.text_quest.database.model.History;
import app.text_quest.database.model.Setting;
import app.text_quest.database.model.State;
import app.text_quest.database.model.Token;
import app.text_quest.database.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends AuditModel {

    @Column(length = 50)
    private String name;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, optional = false, fetch = FetchType.LAZY)
    private Setting setting;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<Token> tokens = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<State> states = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<History> histories = new ArrayList<>();

    public User() {
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Setting getSetting() {
        return setting;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public List<State> getStates() {
        return states;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addToken(Token token) {
        this.tokens.add(token);
        token.setUser(this);
    }

    public void removeToken(Token token) {
        this.tokens.remove(token);
        token.setUser(null);
    }

    public void setSetting(Setting setting) {
        setting.setUser(this);
        this.setting = setting;
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
                "name='" + name + '\'' +
                ", setting=" + setting +
                ", states=" + states +
                ", histories=" + histories +
                '}';
    }
}
