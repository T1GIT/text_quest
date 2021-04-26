package app.database.util.modelFactory.types.node.types;

import app.database.model.node.types.Fork;
import app.database.util.modelFactory.AbstractModelFactory;


/**
 * @see app.database.util.modelFactory.AbstractModelFactory
 */
public class ForkFactory extends AbstractModelFactory<Fork> {
    public Fork create() {
        return new Fork();
    }
}
