package app.text_quest.database.model.user.types;

import app.text_quest.database.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "oauth_users")
public class OauthUser extends User {

    @Column(nullable = false, unique = true, updatable = false)
    private String oauthId;

    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    @Override
    public String toString() {
        return "OauthUser{" +
                "oauthId='" + oauthId + '\'' +
                '}';
    }
}
