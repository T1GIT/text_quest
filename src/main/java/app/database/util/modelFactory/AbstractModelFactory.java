package app.database.util.modelFactory;

import app.database.util.AbstractModel;


/**
 * Creates example models for testing.
 *
 * @param <T> type of a model to create
 */
public abstract class AbstractModelFactory<T extends AbstractModel> {

    /**
     * Creates an example model.
     * Must be overridden in a children classes.
     *
     * @return example model
     */
    public abstract T create();
}
