package com.rodrigosan88.todos.base;

import java.io.Serializable;

public abstract class AbstractEntity<ID> implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract ID getId();

    public abstract Boolean getActive();

    public abstract void setActive(Boolean active);

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractEntity<ID> other = (AbstractEntity<ID>) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        } else {
            return super.hashCode();
        }
    }

    @Override
    public String toString() {
        return getClass().getName() + "[id = " + getId() + "]";
    }

}
