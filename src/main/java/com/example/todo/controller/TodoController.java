// Write your code here
package com.example.todo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoJpaService;

@RestController
public class TodoController {

    @Autowired
    private TodoJpaService todoService;

    @GetMapping("/todos")
    public ArrayList<Todo> getTodoList() {

        return todoService.getTodos();
    }

    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable("id") int todoId) {

        return todoService.getTodoById(todoId);
    }

    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo todo) {

        return todoService.addTodo(todo);
    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable("id") int todoId, @RequestBody Todo todos) {

        return todoService.updateTodo(todoId, todos);
    }

    @DeleteMapping("/todos/{id}")
    public ArrayList<Todo> deleteTodo(@PathVariable("id") int todoId) {

        todoService.deleteTodo(todoId);
        return todoService.getTodos();
    }

}
