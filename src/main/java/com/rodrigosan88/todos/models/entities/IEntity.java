package com.rodrigosan88.todos.models.entities;

import java.io.Serializable;

public interface IEntity<ID> extends Serializable{
	
    public ID getId();

    public Boolean getActive();

    public abstract void setActive(Boolean active);
}
