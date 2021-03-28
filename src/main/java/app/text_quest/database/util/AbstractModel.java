package app.text_quest.database.util;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


/**
 <h2> An abstract class {@link AbstractModel}

 <p> Represents database record in the Object-oriented form.
 Adds attribute {@link AbstractModel#id} to a model.
 */
@MappedSuperclass
public abstract class AbstractModel implements Serializable {

    /**
     Unique identifier for the each record in the database table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    /**
     Constructs a new empty model
     */
    public AbstractModel() {

    }

    /**
     Construct model with a predefined id
     @param id to set
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
