package app.text_quest.database.model.msg.types;

import app.text_quest.database.model.msg.Msg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
//@Table(name = "texts")
public class Text extends Msg {
    @NotNull
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
