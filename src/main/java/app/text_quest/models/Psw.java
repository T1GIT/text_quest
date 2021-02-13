package app.text_quest.models;

import app.text_quest.utils.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "psws")
public class Psw extends AuditModel {
    @Id
    private int id;

    @Column(nullable = false, length = 64)
    private String hash;
    
    @Column(nullable = false, length = 64)
    private String salt;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @MapsId
    private User user;

    public Psw() { }

    public int getId() {
        return id;
    }

    public String getHash() {
        return hash;
    }

    public String getSalt() {
        return salt;
    }

    public User getUser() {
        return user;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Psw{" +
                "id=" + id +
                ", hash='" + hash + '\'' +
                ", salt='" + salt + '\'' +
                ", user=" + user +
                '}';
    }
}
