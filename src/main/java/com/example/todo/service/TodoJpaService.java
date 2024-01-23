package com.example.todo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoJpaRepository;
import com.example.todo.repository.TodoRepository;

@Service
public class TodoJpaService implements TodoRepository{
	
	@Autowired
	private TodoJpaRepository todoRepo;
	
	@Override
	public ArrayList<Todo> getTodos() {
		Collection<Todo> todoList = todoRepo.findAll();
		ArrayList<Todo> todo_arrList = new ArrayList<>(todoList);
		return todo_arrList;
	}

	@Override
	public Todo addTodo(Todo todo) {
		Todo savedTodo = todoRepo.save(todo);
		return savedTodo;
	}

	@Override
	public Todo getTodoById(int todoId) {
		try {
			Todo todo = todoRepo.findById(todoId).get();
			return todo;
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public Todo updateTodo(int todoId, Todo todo) {
		try {
			Todo newTodo = todoRepo.findById(todoId).get();
			if(todo.getTodo() != null) {
				newTodo.setTodo(todo.getTodo());
			}
			if(todo.getStatus() != null) {
				newTodo.setStatus(todo.getStatus());
			}
			if(todo.getPriority() != null) {
				newTodo.setPriority(todo.getPriority());
			}
			todoRepo.save(newTodo);
			return newTodo;
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public void deleteTodo(int todoId) {
		try {
			todoRepo.deleteById(todoId);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

	

}
