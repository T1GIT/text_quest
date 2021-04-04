package app.database.model.node.types.LinkedNode.types;

import app.database.model.Answer;
import app.database.model.Change;
import app.database.model.State;
import app.database.model.Var;
import app.database.model.node.types.LinkedNode.LinkedNode;
import app.database.model.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


/**
 * Object-oriented representation for table <u>questions</u>
 * <p>
 * <b>Storages:</b>
 * Income message from the user
 * <p>
 * <b>Logic:</b>
 * When {@link User user} gets the {@link Question question} he can choose
 * between {@link Answer answers} and every of them has {@link Change impact}
 * on the {@link State state} of {@link Var variables}.
 */
@Entity
@Table(name = "questions")
public class Question extends LinkedNode {

    /**
     * Collection of the possible answers
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Answer> answers = new ArrayList<>();

    public List<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
        answer.setQuestion(this);
    }

    public void removeAnswer(Answer answer) {
        this.answers.remove(answer);
        answer.setQuestion(null);
    }

    @Override
    public String toString() {
        return "OutMsg{" +
                "answers=" + answers +
                '}';
    }
}
