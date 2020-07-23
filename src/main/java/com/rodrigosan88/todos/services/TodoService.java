package com.rodrigosan88.todos.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.rodrigosan88.todos.base.AbstractService;
import com.rodrigosan88.todos.base.BaseService;
import com.rodrigosan88.todos.models.entities.Todo;
import com.rodrigosan88.todos.models.enums.SeverityEnum;
import com.rodrigosan88.todos.repositories.TodoRepository;

@Service
public class TodoService extends AbstractService<Todo, Long> implements BaseService<Todo, Long>{

	@Autowired
	private TodoRepository repo;
	
	@Override
	public Todo createResource(Todo resource) {
		if(resource != null) {
			if(resource.getSeverity() == null) {
				resource.setSeverity(SeverityEnum.LOW);
			}
			resource.setCreated(new Date());
			resource = super.createResource(resource);
		}
		
		return resource;
	}
	
	@Override
	protected JpaRepository<Todo, Long> getRepository() {
		return this.repo;
	}

}
