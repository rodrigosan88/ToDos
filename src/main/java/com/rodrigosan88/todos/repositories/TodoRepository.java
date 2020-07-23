package com.rodrigosan88.todos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodrigosan88.todos.models.entities.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
