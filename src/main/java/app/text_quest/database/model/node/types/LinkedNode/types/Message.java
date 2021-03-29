package app.text_quest.database.model.node.types.LinkedNode.types;

import app.text_quest.database.model.node.types.LinkedNode.LinkedNode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "messages")
public class Message extends LinkedNode {
    @NotNull
    private int delay = 0;

    @Column(columnDefinition = "text", nullable = false)
    private String text;

    public int getDelay() {
        return delay;
    }

    public String getText() {
        return text;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setText(String msg) {
        this.text = msg;
    }

    @Override
    public String toString() {
        return "InMsg{" +
                "delay=" + delay +
                ", msg=" + text +
                '}';
    }
}
