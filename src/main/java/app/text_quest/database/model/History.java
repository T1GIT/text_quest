package app.text_quest.database.model;

import app.text_quest.database.model.msg.Msg;
import app.text_quest.database.model.user.User;
import app.text_quest.database.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "histories")
public class History extends AuditModel {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "msg_id", nullable = false)
    @JsonIgnore
    private Msg msg;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id", nullable = false)
    @JsonIgnore
    private User user;

    public Msg getMsg() {
        return msg;
    }

    public User getUser() {
        return user;
    }

    public void setMsg(Msg msg) {
        this.msg = msg;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "History{" +
                "msg=" + msg +
                '}';
    }
}
