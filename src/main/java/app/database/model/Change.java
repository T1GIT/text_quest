package app.database.model;

import app.database.model.user.User;
import app.database.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


/**
 * Object-oriented representation for table <u>changes</u>
 * <p>
 * <b>Storages:</b>
 * Influence of the each question on the variables' states
 * <p>
 * <b>Logic:</b>
 * When {@link User user} choose the {@link Answer answer} it
 * impacts on {@link State states} of {@link Var variables}.
 */
@Entity
@Table(name = "changes")
public class Change extends AuditModel {

    /**
     * Value impact:
     * {@code End state = start state + val}
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @Column(nullable = false)
    private int val;

    /**
     * Impacted variable
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "var_id", nullable = false)
    @JsonIgnore
    private Var var;

    /**
     * Impacted answer
     * This change will be used if user choose this answer
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * <li> constant
     * </ul>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answers_id", nullable = false)
    @JsonIgnore
    private Answer answer;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Var getVar() {
        return var;
    }

    public void setVar(Var var) {
        this.var = var;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Change{" +
                "val=" + val +
                ", var=" + var +
                ", answer=" + answer +
                '}';
    }
}
