package app.text_quest.database.model;

import app.text_quest.database.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;


@Deprecated
@Entity
@Table(name = "tokens")
public class Token extends AuditModel {

    @Column(nullable = false)
    private String token;

    @CreatedDate
    @Column(name = "creation_date", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @MapsId
    private User user;

    public Token() {
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreationDate() {
        return creationDate;
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
