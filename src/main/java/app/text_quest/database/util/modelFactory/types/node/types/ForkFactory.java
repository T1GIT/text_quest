package app.text_quest.database.util.modelFactory.types.node.types;

import app.text_quest.database.model.node.types.Fork;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;

public class ForkFactory extends AbstractModelFactory<Fork> {
    public Fork create() {
        return new Fork();
    }
}
