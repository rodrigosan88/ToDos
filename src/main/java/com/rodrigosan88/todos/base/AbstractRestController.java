package com.rodrigosan88.todos.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigosan88.todos.exceptions.TodoBusinessException;

public abstract class AbstractRestController <E extends AbstractEntity<I>, I>{

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public E findResource(@PathVariable("id") I id) {

        E resource = null;
		try {
			resource = this.getService().findResource(id);
		} catch (TodoBusinessException e) {
			e.printStackTrace();
		}

        return resource;
    }

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public Page<E> searchResource(Pageable page, @RequestParam(required = false, name = "simpleFilter") String simpleFilter) {

        Page<E> res = null;

        try {
            E resource = !StringUtils.isEmpty(simpleFilter)
                    ? this.jacksonObjectMapper.readValue(simpleFilter, this.getEntityClass())
                    : this.getInstance();

            res = this.getService().searchResource(this.getExemplo(resource), page);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public E createResource(@RequestBody E resource) {
    	try {
			resource = this.getService().createResource(resource);
		} catch (TodoBusinessException e) {
			e.printStackTrace();
		}
        return resource;
    }

    @Transactional(rollbackFor = Exception.class)
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public E modifyResource(@RequestBody E resource, @PathVariable("id") I id) {

        E res = null;

        E persistedResource;

        try {
			persistedResource = this.getService().findResource(id);
			if(persistedResource != null && resource.getId().equals(persistedResource.getId())) {
				res = this.getService().modifyResource(resource);
			}
		} catch (NoSuchElementException | TodoBusinessException e) {
			e.printStackTrace();
		}
        

        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping(path="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public E inactivateResource(@PathVariable("id") I id) {
        E res = null;

        try {
	        if(id != null) {
	                E resource = this.getService().findResource(id);
	                resource.setActive(false);
	                res = this.getService().modifyResource(resource);
	        }
        } catch (NoSuchElementException | TodoBusinessException e) {
        	e.printStackTrace();
        }

        return res;
    }

    protected abstract BaseService<E, I> getService();

    protected Example<E> getExemplo(E recurso) {
        ExampleMatcher matcher = this.getExampleMatcher();
        Example<E> exemplo = Example.of(recurso, matcher);

        return exemplo;
    }

    protected ExampleMatcher getExampleMatcher(){
        return ExampleMatcher.matching().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    }

    @SuppressWarnings("unchecked")
	private String getGenericName()
    {
        return ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getTypeName();
    }

    @SuppressWarnings("unchecked")
	private E getInstance() throws Exception {
        return (E) Class.forName(this.getGenericName()).getConstructor().newInstance();
    }

    @SuppressWarnings("unchecked")
    private Class<E> getEntityClass(){
        Class<E> entityClass = null;

        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType){
            ParameterizedType paramType = (ParameterizedType) type;
            if (paramType.getActualTypeArguments().length == 2){
                if (paramType.getActualTypeArguments()[1] instanceof TypeVariable){
                    throw new IllegalArgumentException("Could not guess entity class by reflection");
                }
                else{
                    entityClass = (Class<E>) paramType.getActualTypeArguments()[0];
                }
            }
            else{
                entityClass = (Class<E>) paramType.getActualTypeArguments()[0];
            }
        }
        else{
            throw new IllegalArgumentException("Could not guess entity class by reflection");
        }

        return entityClass;
    }

}
