package app.text_quest.database.model.user.types;

import app.text_quest.database.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Object-oriented representation for table <u>mail_users</u>
 * <p>
 * <b>Storages:</b>
 * Creditionals of users, authorised via email
 */
@Entity
@Table(name = "mail_users")
public class MailUser extends User {

    /**
     * Email, used for registration
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * <li> constant
     * <li> unique
     * </ul>
     */
    @Column(nullable = false, unique = true, updatable = false)
    private String mail;

    /**
     * Hashed password
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * <li> length {@literal <} 1181
     * </ul>
     */
    @Column(nullable = false, length = 1180)
    private String psw;

    /**
     * Email confirmation
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @Column(nullable = false)
    private boolean verified;

    /**
     * Token to identify user from the email
     * Basically it is inserted into link in the button in mail.
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @Column(nullable = false)
    private String token;

    public String getMail() {
        return mail;
    }

    public String getPsw() {
        return psw;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getToken() {
        return token;
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

    public void setToken(String token) {
        this.token = token;
    }
}
