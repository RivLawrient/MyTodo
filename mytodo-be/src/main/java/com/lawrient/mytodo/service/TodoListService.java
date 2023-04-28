package com.lawrient.mytodo.service;

import com.lawrient.mytodo.dto.response.DataResponse;
import com.lawrient.mytodo.entity.Todo;
import com.lawrient.mytodo.entity.TodoList;
import com.lawrient.mytodo.repository.TodoListRepository;
import com.lawrient.mytodo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private TodoRepository todoRepository;

    public TodoList create(String task, Long todoId, Boolean isComplete){
        TodoList list = new TodoList();
        list.setTask(task);

        Optional<Todo> byId = todoRepository.findById(todoId);


        list.setTodo(byId.get());
        list.setIsComplete(isComplete);

        return todoListRepository.save(list);
    }

    public TodoList edit(Long todoListId, String task, Long todoId, Boolean isCompelete){
        TodoList list = new TodoList();
        list.setId(todoListId);
        list.setTask(task);

        Optional<Todo> byId = todoRepository.findById(todoId);
        list.setTodo(byId.get());
        list.setIsComplete(isCompelete);

        return todoListRepository.saveAndFlush(list);
    }

    public ResponseEntity<DataResponse> delete(Long todoListId){


        if (todoListRepository.existsById(todoListId)){
            todoListRepository.deleteById(todoListId);
            return new ResponseEntity<>(
                    new DataResponse(200,"TodoList Deleted", null), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(
                    new DataResponse(404,"TodoList Not Found", null), HttpStatus.NOT_FOUND);
        }
    }

    public List<TodoList> find(Long todoId){
        Optional<Todo> byId = todoRepository.findById(todoId);
        return todoListRepository.findByTodo(byId.get());
    }

}
