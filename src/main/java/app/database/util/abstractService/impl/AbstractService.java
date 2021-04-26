package app.database.util.abstractService.impl;

import app.database.util.AbstractModel;
import app.database.util.abstractService.ServiceInterface;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @param <ModelClass>      type of a model to work with
 * @param <repositoryClass> repository to interact with the given model
 * @see app.database.util.abstractService.ServiceInterface
 */
public abstract class AbstractService
        <ModelClass extends AbstractModel,
                repositoryClass extends JpaRepository<ModelClass, Long>>
        implements ServiceInterface<ModelClass> {

    /**
     * A repository to interact with database
     */
    protected final repositoryClass repository;

    public AbstractService(repositoryClass repository) {
        this.repository = repository;
    }

    @Override
    public ModelClass getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ModelClass add(ModelClass model) {
        return repository.saveAndFlush(model);
    }

    @Override
    public void delete(ModelClass model) {
        repository.delete(model);
    }

    @Override
    public ModelClass update(ModelClass model) {
        return repository.saveAndFlush(model);
    }

    @Override
    public List<ModelClass> getAll() {
        return repository.findAll();
    }
}
