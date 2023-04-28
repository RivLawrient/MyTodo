package com.lawrient.mytodo.service;

import com.lawrient.mytodo.dto.response.DataResponse;
import com.lawrient.mytodo.entity.Todo;
import com.lawrient.mytodo.entity.TodoList;
import com.lawrient.mytodo.entity.User;
import com.lawrient.mytodo.repository.TodoRepository;
import com.lawrient.mytodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoListService todoListService;

    @Autowired
    private UserRepository userRepository;

    public Todo create(String title, String email){
        Todo todo = new Todo();
        todo.setTitle(title);
        User user = new User(email);
        user.setName(userRepository.getReferenceById(email).getName());
        todo.setUser(user);

        return todoRepository.save(todo);

    }

    public Todo update(Long id, String title, String email){

        Todo todo = new Todo();
        todo.setId(id);
        todo.setTitle(title);
        todo.setUser(new User(email));

        return todoRepository.saveAndFlush(todo);
    }

    public ResponseEntity<DataResponse> delete(Long id){
        if (todoRepository.existsById(id)){
            List<TodoList> list = todoListService.find(id);
            for (TodoList hasil : list) {
                todoListService.delete(hasil.getId());
            }
            todoRepository.deleteById(id);
            return new ResponseEntity<>(
                    new DataResponse(200,"Todo Deleted", null), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(
                    new DataResponse(404,"Todo Not Found", null), HttpStatus.NOT_FOUND);
        }
    }

    public List<Todo> find(String email){
        return todoRepository.findByUser(new User(email));
    }

    public Optional<Todo> findById(Long id){
        return todoRepository.findById(id);
    }
}
