package app.text_quest.database.model.user.types;

import app.text_quest.database.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "basic_users")
public class MailUser extends User {

    @Column(nullable = false, unique = true, updatable = false)
    private String mail;

    @Column(nullable = false, length = 1180)
    private String psw;

    @Column(nullable = false)
    private boolean verified;

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
