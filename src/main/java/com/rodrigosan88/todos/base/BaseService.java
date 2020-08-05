package com.rodrigosan88.todos.base;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rodrigosan88.todos.exceptions.TodoBusinessException;

public interface BaseService<E, I> {

    E findResource(I id) throws TodoBusinessException;
    Page<E> searchResource(Example<E> resourceExample, Pageable pageable) throws TodoBusinessException;
    E createResource(E resource) throws TodoBusinessException;
    E modifyResource(E resource) throws TodoBusinessException;

}
