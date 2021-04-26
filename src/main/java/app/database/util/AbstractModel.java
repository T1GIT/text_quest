package app.database.util;

import app.controller.util.exception.game.types.ModelNotFoundException;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;


/**
 * Represents database record in the Object-oriented form.
 * Adds attribute {@link AbstractModel#id} to a model.
 */
@MappedSuperclass
public abstract class AbstractModel implements Serializable {

    /**
     * Unique identifier for the each record in the database table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    /**
     * Class constructor.
     */
    public AbstractModel() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                '}';
    }
}
