package app.text_quest.models;

import app.text_quest.models.Msg.Msg;
import app.text_quest.utils.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "states")
public class Hist extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "msg_id", nullable = false)
    @JsonIgnore
    private Msg msg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    @JsonIgnore
    private User user;

    public Hist() { }

    public int getId() {
        return id;
    }

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
        return "Hist{" +
                "id=" + id +
                ", msg=" + msg +
                ", user=" + user +
                '}';
    }
}
