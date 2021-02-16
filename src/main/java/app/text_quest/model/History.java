package app.text_quest.model;

import app.text_quest.model.msg.Msg;
import app.text_quest.util.AbstractModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "histories")
public class History extends AbstractModel {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "msg_id", nullable = false)
    @JsonIgnore
    private Msg msg;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id", nullable = false)
    @JsonIgnore
    private User user;

    public History() { }

    public long getId() {
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
        return "History{" +
                "msg=" + msg +
                '}';
    }
}
