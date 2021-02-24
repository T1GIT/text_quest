package app.text_quest.database.model.node.types.LinkedNode.types;

import app.text_quest.database.model.msg.Msg;
import app.text_quest.database.model.node.types.LinkedNode.LinkedNode;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "in_msg")
public class InMsg extends LinkedNode {
    @NotNull
    private int delay = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "msg_id")
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
