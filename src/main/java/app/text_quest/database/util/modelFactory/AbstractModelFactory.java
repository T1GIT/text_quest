package app.text_quest.database.util.modelFactory;

import app.text_quest.database.util.AbstractModel;


/**
 <h2> An abstract class {@link AbstractModelFactory}

 <p> Intended to creating example models for testing

 @param <T> type of a model to create */
public abstract class AbstractModelFactory<T extends AbstractModel> {

    /**
     Creates an example model.
     Must be overridden in a children classes

     @return example model
     */
    public abstract T create();
}
