package app.text_quest.database.util;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public abstract class AbstractServiceImpl<T extends AbstractModel, R extends JpaRepository<T, Long>> implements AbstractService<T> {

    protected final R repository;

    public AbstractServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public T getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public T add(T model) {
        return repository.saveAndFlush(model);
    }

    @Override
    public void delete(T model) {
        repository.delete(model);
    }

    @Override
    public T update(T model) {
        return repository.saveAndFlush(model);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }
}
