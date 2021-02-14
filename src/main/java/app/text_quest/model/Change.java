package app.text_quest.model;

import app.text_quest.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "changes")
public class Change extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
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

    public long getId() {
        return id;
    }

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
                "id=" + id +
                ", val=" + val +
                ", var=" + var +
                ", answer=" + answer +
                '}';
    }
}
