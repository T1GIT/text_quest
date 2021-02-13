package app.text_quest.model.Node.types.LinkedNode.types;

import app.text_quest.model.Msg.Msg;
import app.text_quest.model.Node.types.LinkedNode.LinkedNode;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "in_msg")
public class InMsg extends LinkedNode {

    @Column(nullable = false)
    private int delay = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "msg_id", nullable = false)
    @JsonIgnore
    private Msg msg;

    public InMsg() { }

    public int getDelay() {
        return delay;
    }

    public Msg getMsg() {
        return msg;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setMsg(Msg msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "InMsg{" +
                "delay=" + delay +
                ", msg=" + msg +
                '}';
    }
}
