package app.text_quest.models;

import app.text_quest.utils.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "changes")
public class Change extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
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

    public int getId() {
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
}
