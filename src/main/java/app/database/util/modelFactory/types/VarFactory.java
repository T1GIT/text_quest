package app.database.util.modelFactory.types;

import app.database.model.Var;
import app.database.util.modelFactory.AbstractModelFactory;


/**
 * @see app.database.util.modelFactory.AbstractModelFactory
 */
public class VarFactory extends AbstractModelFactory<Var> {
    private final String name = "varName";

    public Var create() {
        Var var = new Var();
        var.setName(this.name);
        return var;
    }

    public String getName() {
        return name;
    }
}
