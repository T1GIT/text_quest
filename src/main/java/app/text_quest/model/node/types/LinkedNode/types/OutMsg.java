package app.text_quest.model.node.types.LinkedNode.types;

import app.text_quest.model.Answer;
import app.text_quest.model.node.types.LinkedNode.LinkedNode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "out_msg")
public class OutMsg extends LinkedNode {

    @OneToMany(mappedBy = "outMsg", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    public OutMsg() { }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
        answer.setOutMsg(this);
    }

    public void removeAnswer(Answer answer) {
        this.answers.remove(answer);
        answer.setOutMsg(null);
    }

    @Override
    public String toString() {
        return "OutMsg{" +
                "answers=" + answers +
                '}';
    }
}
