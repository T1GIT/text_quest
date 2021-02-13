package app.text_quest.models;

import app.text_quest.utils.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "req")
public class Req extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int min;
    private int max;
    private int equal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "var_id", nullable = false)
    @JsonIgnore
    private Var var;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branches_id", nullable = false)
    @JsonIgnore
    private Branch branch;

    public Req() { }

    public int getId() {
        return id;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getEqual() {
        return equal;
    }

    public Var getVar() {
        return var;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setEqual(int is) {
        this.equal = is;
    }

    public void setVar(Var var) {
        this.var = var;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
