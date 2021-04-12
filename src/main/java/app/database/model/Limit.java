package app.database.model;

import app.database.model.node.Node;
import app.database.model.node.types.Fork;
import app.database.model.user.User;
import app.database.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


/**
 * Object-oriented representation for table <u>limits</u>
 * <p>
 * <b>Storages:</b>
 * Limits for the choosing a suitable continuing
 * <p>
 * <b>Logic:</b>
 * When {@link User user}  reaches {@link Fork fork} game can choice the right next
 * {@link Node node} depend on {@link Limit limits}: required {@link State state}  of
 * {@link Var variables}.
 * <p>
 * Variable state must be more then {@link Limit#min} if it set,
 * equal to {@link Limit#equal} if it set and less then {@link Limit#max} if it set.
 */
@Entity
@Table(name = "limits")
public class Limit extends AuditModel {

    /**
     * Low bound of the variable state.
     * If it set variable's state must be more then its value.
     */
    private int min;

    /**
     * High bound of the variable state.
     * If it set variable's state must be less then its value.
     */
    private int max;

    /**
     * Low bound of the variable state.
     * If it set variable's state must be equal its value.
     */
    private int equal;

    /**
     * Defines, what variable will be bounded.
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "var_id", nullable = false)
    @JsonIgnore
    private Var var;

    /**
     * Branch that have this limit for transferring to branches node.
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branches_id", nullable = false)
    @JsonIgnore
    private Branch branch;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getEqual() {
        return equal;
    }

    public void setEqual(int is) {
        this.equal = is;
    }

    public Var getVar() {
        return var;
    }

    public void setVar(Var var) {
        this.var = var;
    }

    public Branch getBranch() {
        return branch;
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
