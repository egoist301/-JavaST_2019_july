package by.training.catalog.bean;

import java.util.Objects;

/**
 * Abstract class from which all other entity classes are inherited.
 */
public class Entity {
    /**
     * Unique entity id.
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
     * Id getter.
     *
     * @return entity id.
     */
    public long getId() {
        return id;
    }

    /**
     * Id setter.
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
