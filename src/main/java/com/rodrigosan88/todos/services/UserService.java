package com.rodrigosan88.todos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.rodrigosan88.todos.base.AbstractService;
import com.rodrigosan88.todos.models.entities.User;
import com.rodrigosan88.todos.repositories.UserRepository;

@Service
public class UserService extends AbstractService<User, Long>{

	@Autowired
	private UserRepository repo;
	
	@Override
	protected JpaRepository<User, Long> getRepository() {
		return this.repo;
	}

}
