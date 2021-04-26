package app.database.util.modelFactory.types;

import app.database.model.Branch;
import app.database.model.Condition;
import app.database.model.Var;
import app.database.util.modelFactory.AbstractModelFactory;


/**
 * @see app.database.util.modelFactory.AbstractModelFactory
 */
public class LimitFactory extends AbstractModelFactory<Condition> {

    private final static VarFactory varFactory = new VarFactory();
    private final static BranchFactory branchFactory = new BranchFactory();
    private final Var var = varFactory.create();
    private final Branch branch = branchFactory.create();
    private final int max = 100;
    private final int equal = 50;
    private final int min = 10;

    public Condition create() {
        Condition condition = new Condition();
        condition.setBranch(branchFactory.create());
        condition.setMax(this.max);
        condition.setEqual(this.equal);
        condition.setMin(this.min);
        condition.setVar(varFactory.create());
        return condition;
    }

    public Var getVar() {
        return var;
    }

    public Branch getBranch() {
        return branch;
    }

    public int getMax() {
        return max;
    }

    public int getEqual() {
        return equal;
    }

    public int getMin() {
        return min;
    }
}
