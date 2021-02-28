package app.text_quest.database.model;

import app.text_quest.database.model.user.User;
import app.text_quest.database.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Deprecated
@Entity
@Table(name = "tokens")
public class Token extends AuditModel {

    @Column(nullable = false)
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @MapsId
    private User user;

    public Token() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token +
                '}';
    }
}
