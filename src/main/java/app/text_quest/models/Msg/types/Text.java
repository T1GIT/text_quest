package app.text_quest.models.Msg.types;

import app.text_quest.models.Msg.Msg;

import javax.persistence.*;

@Entity
@Table(name = "texts")
public class Text extends Msg {
    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    public Text() { }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Text{" +
                "text='" + text + '\'' +
                '}';
    }
}
