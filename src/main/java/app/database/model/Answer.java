package app.database.model;

import app.database.model.node.types.LinkedNode.types.Question;
import app.database.model.user.User;
import app.database.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Object-oriented representation for table <u>answers</u>
 * <p>
 * <b>Storages:</b>
 * Possible user's responses to the question
 * <p>
 * <b>Logic:</b>
 * When {@link User user} gets the {@link Question question} he can choose
 * between {@link Answer answers} and every of them has {@link Change impact}
 * on the {@link State state} of {@link Var variables}.
 */
@Entity
@Table(name = "answers")
public class Answer extends AuditModel {// TODO: May be add "aborted"(link to next node) and "delay" if user will not answer

    /**
     * All impacts of this answer
     */
    @JsonIgnore
    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Change> changes = new ArrayList<>();

    /**
     * Text of the answer
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @Column(columnDefinition = "text", nullable = false)
    private String text;

    /**
     * The question, whom this answer is
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonIgnore
    private Question question;

    @ManyToMany
    @JoinTable(
            name = "student_answers",
            joinColumns = @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public List<Change> getChanges() {
        return changes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void addChange(Change change) {
        this.changes.add(change);
        change.setAnswer(this);
    }

    public void removeChange(Change change) {
        this.changes.remove(change);
        change.setAnswer(null);
    }

    public void addUser(User user) {
        this.users.add(user);
        user.addAnswer(this);
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.removeAnswer(this);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "changes=" + changes +
                ", text='" + text + '\'' +
                '}';
    }
}
