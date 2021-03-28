package app.text_quest.database.model;

import app.text_quest.database.model.user.User;
import app.text_quest.database.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 <h2> A class {@link Refresh}

 <p> It's object-oriented representation for table <u>tokens</u>

 <p> <b> Storages: </b>
 <p> Refresh tokens of users
 <p> <b> Logic: </b>
 <p> If JWT token doesn't exist or invalid then if request contains
 {@link Refresh} user can be identified by that and receive fresh JWT
 with updating refresh token.
 */
@Entity
@Table(name = "tokens")
public class Refresh extends AuditModel {

    /**
     The value of the token.
     <p> <b> Constraints: </b>
     <ul>
     <li> required
     */
    @Column(nullable = false)
    private String value;

    /**
     The user, token owner.
     <p> <b> Constraints: </b>
     <ul>
     <li> required
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id", nullable = false)
    @JsonIgnore
    private User user;

    public String getValue() {
        return value;
    }

    public void setValue(String token) {
        this.value = token;
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
                "value='" + value +
                '}';
    }
}
