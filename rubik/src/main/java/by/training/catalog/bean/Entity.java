package by.training.catalog.bean;

import java.util.Objects;

public class Entity {
    private long id;
    private boolean blocked;

    Entity(final long idNew, final boolean blockedNew) {
        id = idNew;
        blocked = blockedNew;
    }

    public long getId() {
        return id;
    }

    public void setId(final long idNew) {
        id = idNew;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(final boolean blockedNew) {
        blocked = blockedNew;
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
        return getId() == entity.getId()
                && isBlocked() == entity.isBlocked();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isBlocked());
    }

    @Override
    public String toString() {
        return "id=" + id + ", blocked=" + blocked;
    }
}
