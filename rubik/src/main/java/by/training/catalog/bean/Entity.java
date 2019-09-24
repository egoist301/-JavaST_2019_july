package by.training.catalog.bean;

import java.util.Objects;

public class Entity {
    private long id;

    Entity(final long idNew) {
        id = idNew;
    }

    public long getId() {
        return id;
    }

    public void setId(final long idNew) {
        id = idNew;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
