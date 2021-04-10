package app.database.util;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


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

    /**
     * Class constructor, specifying id
     *
     * @param id record's identifier
     */
    public AbstractModel(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                '}';
    }
}
