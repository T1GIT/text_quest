package app.text_quest.model;

import app.text_quest.model.Msg.types.Text;
import app.text_quest.model.Node.types.LinkedNode.types.OutMsg;
import app.text_quest.util.AbstractEntity;
import app.text_quest.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "answers")
public class Answer extends AbstractEntity {

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Change> changes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "texts_id", nullable = false)
    @JsonIgnore
    private Text text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "out_msg_id", nullable = false)
    @JsonIgnore
    private OutMsg outMsg;

    public Answer() { }

    public List<Change> getChanges() {
        return changes;
    }

    public Text getText() {
        return text;
    }

    public OutMsg getOutMsg() {
        return outMsg;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public void setOutMsg(OutMsg outMsg) {
        this.outMsg = outMsg;
    }

    public void addChange(Change change) {
        this.changes.add(change);
        change.setAnswer(this);
    }

    public void removeChange(Change change) {
        this.changes.remove(change);
        change.setAnswer(null);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "changes=" + changes +
                ", text=" + text +
                ", outMsg=" + outMsg +
                '}';
    }
}
