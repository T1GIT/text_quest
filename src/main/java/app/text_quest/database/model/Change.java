package app.text_quest.database.model;

import app.text_quest.database.util.AbstractModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "changes")
public class Change extends AbstractModel {
    
    @Column(nullable = false)
    private int val;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "var_id", nullable = false)
    @JsonIgnore
    private Var var;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answers_id", nullable = false)
    @JsonIgnore
    private Answer answer;

    public Change() { }

    public int getVal() {
        return val;
    }

    public Var getVar() {
        return var;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setVar(Var var) {
        this.var = var;
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
