package app.text_quest.util.modelFactory.types;

import app.text_quest.model.Var;
import app.text_quest.util.modelFactory.AbstractModelFactory;

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
