package app.text_quest.database.util;

import java.util.List;


public interface AbstractService<T extends AbstractModel> {

    T getById(Long id);

    T add(T model);

    void delete(T model);

    T update(T model);

    List<T> getAll();

}
