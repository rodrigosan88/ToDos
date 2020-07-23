package com.rodrigosan88.todos.base;

import java.util.NoSuchElementException;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractService<E extends AbstractEntity<I>, I> implements BaseService<E, I>{

    @Override
    public E findResource(I id) {

        E resource = this.getRepository().findById(id).get();

        return resource;
    }

    @Override
    public Page<E> searchResource(Example<E> resourceExample, Pageable pageable) {

        Page<E> page = this.getRepository().findAll(resourceExample, pageable);

        return page;
    }

    @Override
    public E createResource(E resource) {

    	resource = this.getRepository().save(resource);

        return resource;
    }

    @Override
    public E modifyResource(E resource) throws NoSuchElementException {

        if(resource != null && resource.getId() != null){
            this.getRepository().findById(resource.getId()).get();
            resource = this.getRepository().save(resource);
        }

        return resource;
    }

    protected abstract JpaRepository<E, I> getRepository();
}