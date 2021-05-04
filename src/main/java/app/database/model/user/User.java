package app.database.model.user;

import app.database.model.*;
import app.database.util.AuditModel;
import app.database.util.enums.Role;
import app.security.util.constant.SecretLength;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Object-oriented representation for table <u>users</u>
 * <p>
 * Base entity for specified types of users, using JOINED strategy for an inheritance
 * <p>
 * <b>Storages:</b>
 * Users information
 * <p>
 * <b>Logic:</b>
 * The base {@link User} class, linked with: {@link Setting}, {@link Refresh}, {@link State}.
 * Contains user's name.
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends AuditModel {

    /**
     * User's name.
     */
    @Column(length = 50)
    private String name = null;

    /**
     * Id to subscribe in the socket url
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> unique
     * </ul>
     */
    @Column(unique = true, length = SecretLength.SOCKET_ID)
    private String socketId;

    /**
     * Role, specifying user's rights
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /**
     * Refresh tokens of the user
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<Refresh> refreshes = new ArrayList<>();
    /**
     * State of variables for this user
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<State> states = new ArrayList<>();

    /**
     * Messages received by this user
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<History> histories = new ArrayList<>();

    /**
     * Settings of the game, belong to user
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, optional = false, fetch = FetchType.LAZY)
    private Setting setting;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_answer",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private final List<Answer> answers = new LinkedList<>();

    public String getName() {
        return name;
    }

    public String getSocketId() {
        return socketId;
    }

    public Role getRole() {
        return role;
    }

    public Setting getSetting() {
        return setting;
    }

    public List<Refresh> getTokens() {
        return refreshes;
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

    public void setSocketId(String socketId) {
        this.socketId = socketId;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
        setting.setUser(this);
    }

    public void addToken(Refresh refresh) {
        this.refreshes.add(refresh);
        refresh.setUser(this);
    }

    public void removeToken(Refresh refresh) {
        this.refreshes.remove(refresh);
        refresh.setUser(null);
    }

    public void addHistory(History history) {
        this.histories.add(history);
        history.setUser(this);
    }

    public void removeHistory(History history) {
        this.histories.remove(history);
        history.setUser(null);
    }

    public void addState(State state) {
        this.states.add(state);
        state.setUser(this);
    }

    public void removeState(State state) {
        this.states.remove(state);
        state.setUser(null);
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void removeAnswer(Answer answer) {
        this.answers.remove(answer);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", setting=" + setting +
                ", states=" + states +
                '}';
    }
}
