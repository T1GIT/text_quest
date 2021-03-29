package app.text_quest.database.util.modelFactory.types;

import app.text_quest.database.model.Var;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;


/**
 * @see app.text_quest.database.util.modelFactory.AbstractModelFactory
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
