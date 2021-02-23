package app.text_quest.model;

import app.text_quest.util.AbstractModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Arrays;


@Entity
@Table(name = "tokens") // TODO: 22.02.2021
public class Token extends AbstractModel {

    @Column(nullable = false, length = 512)
    private byte[] hash;

    @Column(nullable = false, length = 16)
    private byte[] salt;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @MapsId
    private User user;

    public Token() {
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

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Psw{" +
                "hash='" + Arrays.toString(hash) + '\'' +
                ", salt='" + Arrays.toString(salt) + '\'' +
                '}';
    }
}
