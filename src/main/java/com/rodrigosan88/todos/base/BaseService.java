package com.rodrigosan88.todos.base;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<E, I> {

    E findResource(I id);
    Page<E> searchResource(Example<E> resourceExample, Pageable pageable);
    E createResource(E resource);
    E modifyResource(E resource);

}
