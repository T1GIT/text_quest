package app.text_quest.model;

import app.text_quest.util.AbstractEntity;
import app.text_quest.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "psws")
public class Psw extends AbstractEntity {

    @Column(nullable = false, length = 64)
    private String hash;
    
    @Column(nullable = false, length = 64)
    private String salt;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @MapsId
    private User user;

    public Psw() { }

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
                "hash='" + hash + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
