package app.text_quest.database.model;

import app.text_quest.database.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


/**
 <h2> A class {@link Var}

 <p> It's object-oriented representation for table <u>vars</u>

 <p> <b> Storages: </b>
 <p> Variables' names
 <p> <b> Logic: </b>
 <p> When user make a {@link Answer choice} game changes the {@link State state}
 of {@link Change any} variables. So, all available variables are in this table.
 */
@Entity
@Table(name = "limits")
public class Limit extends AuditModel {

    private int min;
    private int max;
    private int equal;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "var_id", nullable = false)
    @JsonIgnore
    private Var var;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "branches_id", nullable = false)
    @JsonIgnore
    private Branch branch;

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

    @Override
    public String toString() {
        return "Limit{" +
                "min=" + min +
                ", max=" + max +
                ", equal=" + equal +
                ", var=" + var +
                '}';
    }
}
