package com.rodrigosan88.todos.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigosan88.todos.base.AbstractRestController;
import com.rodrigosan88.todos.base.BaseService;
import com.rodrigosan88.todos.models.entities.User;
import com.rodrigosan88.todos.services.UserService;

@RestController
@RequestMapping(UserRestController.PATH)
public class UserRestController extends AbstractRestController<User, Long>{

	public static final String PATH = "/api/v1/users";
	
	@Autowired
	private UserService service;
	
	@Override
	protected BaseService<User, Long> getService() {
		return service;
	}

}
