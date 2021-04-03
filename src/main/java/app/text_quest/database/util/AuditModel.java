package app.text_quest.database.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;


/**
 * Is a wrap for the {@link AbstractModel}, adding to models attributes for the auditing.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public abstract class AuditModel extends AbstractModel {

    /**
     * Date of the record creating
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * <li> constant
     * </ul>
     */
    @JsonIgnore
    @CreatedDate
    @Column(name = "createdAt", nullable = false, updatable = false)
    private Date createdAt;

    /**
     * Date of the last record updating
     * <p>
     * <b>Constraints:</b>
     * <ul>
     * <li> required
     * </ul>
     */
    @JsonIgnore
    @LastModifiedDate
    @Column(name = "updatedAt", nullable = false)
    private Date updatedAt;

    /**
     * Class constructor
     */
    public AuditModel() {
        super();
    }

    /**
     * Class constructor specified an id
     *
     * @param id to set
     */
    public AuditModel(long id) {
        super(id);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "AuditModel{" +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
