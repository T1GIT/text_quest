package app.text_quest.model.msg.types;

import app.text_quest.model.msg.Msg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "texts")
public class Text extends Msg {
    @Column(columnDefinition = "TEXT")
    private String val;

    public Text() { }

    public String getVal() {
        return val;
    }

    public void setVal(String text) {
        this.val = text;
    }

    @Override
    public String toString() {
        return "Text{" +
                "val='" + val + '\'' +
                '}';
    }
}
