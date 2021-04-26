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
 * {@link Node node} depend on {@link Condition limits}: required {@link State state}  of
 * {@link Var variables}.
 * <p>
 * Variable state must be more then {@link Condition#min} if it set,
 * equal to {@link Condition#equal} if it set and less then {@link Condition#max} if it set.
 */
@Entity
@Table(name = "conditions")
public class Condition extends AuditModel {

    /**
     * Low bound of the variable state.
     * If it set variable's state must be more then its value.
     */
    private Integer min;

    /**
     * High bound of the variable state.
     * If it set variable's state must be less then its value.
     */
    private Integer max;

    /**
     * Low bound of the variable state.
     * If it set variable's state must be equal its value.
     */
    private Integer equal;

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

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getEqual() {
        return equal;
    }

    public void setEqual(Integer is) {
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
