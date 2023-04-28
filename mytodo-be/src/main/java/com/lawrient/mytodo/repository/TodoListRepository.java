package com.lawrient.mytodo.repository;

import com.lawrient.mytodo.entity.Todo;
import com.lawrient.mytodo.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {

    List<TodoList> findByTodo(Todo todo);
}
