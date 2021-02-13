package app.text_quest.models.Node.types.LndNode.types;

import app.text_quest.models.Answer;
import app.text_quest.models.Node.types.LndNode.LndNode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "out_msg")
public class OutMsg extends LndNode {
    @OneToMany(mappedBy = "outMsg", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

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
}
