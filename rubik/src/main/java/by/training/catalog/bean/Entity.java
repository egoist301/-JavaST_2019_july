package by.training.catalog.bean;

import java.util.Objects;

/**
 * Abstract class Entity.
 */
public class Entity {
    /**
     * Entity id.
     */
    private long id;

    /**
     * Constructor with parameter. Super constructor.
     *
     * @param idNew entity id.
     */
    Entity(final long idNew) {
        id = idNew;
    }

    /**
     * Getter.
     *
     * @return entity id.
     */
    public long getId() {
        return id;
    }

    /**
     * Setter.
     *
     * @param idNew entity id.
     */
    public void setId(final long idNew) {
        id = idNew;
    }

    /**
     * Equal entity with other any object.
     *
     * @param oNew any object.
     * @return equal or not.
     */
    @Override
    public boolean equals(final Object oNew) {
        if (this == oNew) {
            return true;
        }
        if (!(oNew instanceof Entity)) {
            return false;
        }
        Entity entity = (Entity) oNew;
        return getId() == entity.getId();
    }

    /**
     * Hash code.
     *
     * @return hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     * Object to string.
     *
     * @return object in string.
     */
    @Override
    public String toString() {
        return "id=" + id;
    }
}
