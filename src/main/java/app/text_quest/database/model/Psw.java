package app.text_quest.database.model;

import app.text_quest.database.util.AbstractModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Arrays;


@Entity
@Table(name = "psws")
public class Psw extends AbstractModel {

    @Column(nullable = false, length = 512)
    private byte[] hash;

    @Column(nullable = false, length = 16)
    private byte[] salt;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @MapsId
    private User user;

    public Psw() {
    }

    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public User getUser() {
        return user;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Psw{" +
                "hash='" + Arrays.toString(hash) + '\'' +
                ", salt='" + Arrays.toString(salt) + '\'' +
                '}';
    }
}
