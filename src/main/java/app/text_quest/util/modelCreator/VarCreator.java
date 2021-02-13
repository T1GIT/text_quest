package app.text_quest.util.modelCreator;

import app.text_quest.model.Psw;
import app.text_quest.model.Var;

public class VarCreator {
    public static Var create() {
        Var var = new Var();
        var.setName("");
        return var;
    }
}
