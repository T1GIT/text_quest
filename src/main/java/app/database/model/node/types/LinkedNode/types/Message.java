package app.database.model.node.types.LinkedNode.types;

import app.database.model.node.types.LinkedNode.LinkedNode;
import app.database.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * Object-oriented representation for table <u>messages</u>
 * <p>
 * <b>Storages:</b>
 * Outcome messages
 * <p>
 * <b>Logic:</b>
 * After {@link Message#delay} seconds game will send this {@link Message message} to the {@link User user}.
 */
@Entity
@Table(name = "messages")
public class Message extends LinkedNode {

    /**
     * Amount of seconds, after what game will send this message
     */
    @NotNull
    private int delay = 0;

    /**
     * Message has content, specified in this attribute
     */
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
        return "Message{" +
                "delay=" + delay +
                ", msg=" + text +
                '}';
    }
}
