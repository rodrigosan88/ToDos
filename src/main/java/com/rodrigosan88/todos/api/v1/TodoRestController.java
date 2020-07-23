package com.rodrigosan88.todos.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigosan88.todos.base.AbstractRestController;
import com.rodrigosan88.todos.base.BaseService;
import com.rodrigosan88.todos.models.entities.Todo;
import com.rodrigosan88.todos.services.TodoService;

@RestController
@RequestMapping(TodoRestController.PATH)
public class TodoRestController extends AbstractRestController<Todo, Long>{
	
	public static final String PATH = "/todos";

	@Autowired
	private TodoService todoService;
	
	@Override
	protected BaseService<Todo, Long> getService() {
		return this.todoService;
	}

}
