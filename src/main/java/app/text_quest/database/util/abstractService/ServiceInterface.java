package app.text_quest.database.util.abstractService;

import app.text_quest.database.util.AbstractModel;

import java.util.List;


/**
 <h2> An interface {@link ServiceInterface}

 <p> Provides methods for interaction with database on the business-logic level

 @param <ModelClass> type of model */
public interface ServiceInterface<ModelClass extends AbstractModel> {

    /**
     Gets model from the database by the id

     @param id of the record
     @return model if id was founded else - null
     */
    ModelClass getById(Long id);

    /**
     Adds model to the database

     @param model to add
     @return added model
     */
    ModelClass add(ModelClass model);

    /**
     Deletes model from the database

     @param model to delete
     */
    void delete(ModelClass model);

    /**
     Updates model data

     @param model changed model
     @return changed model
     */
    ModelClass update(ModelClass model);

    /**
     Gets all models of the given type

     @return list of models
     */
    List<ModelClass> getAll();

}
