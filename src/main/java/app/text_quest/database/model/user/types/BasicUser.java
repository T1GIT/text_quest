package app.text_quest.database.model.user.types;

import app.text_quest.database.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "basic_users")
public class BasicUser extends User {

    @Column(nullable = false, unique = true, updatable = false)
    private String mail;

    @Column(nullable = false)
    private String psw;

    @Column(nullable = false)
    private boolean verified;

    public BasicUser() {
    }

    public String getMail() {
        return mail;
    }

    public String getPsw() {
        return psw;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public void setMail(String main) {
        this.mail = main;
    }
}
