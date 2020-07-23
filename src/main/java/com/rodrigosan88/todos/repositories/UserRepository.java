package com.rodrigosan88.todos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigosan88.todos.models.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
