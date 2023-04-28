package com.lawrient.mytodo.controller;

import com.lawrient.mytodo.dto.TodoListRequest;
import com.lawrient.mytodo.dto.response.DataResponse;
import com.lawrient.mytodo.security.GetAuthorization;
import com.lawrient.mytodo.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://mytodo.sannd.site")
@RestController
@RequestMapping("api/todolist")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @Autowired
    private GetAuthorization getAuthorization;

    @PostMapping
    public ResponseEntity<DataResponse> create(@RequestBody TodoListRequest request){
        if(getAuthorization.getAuth().getStatusCode().is4xxClientError()){
            return getAuthorization.getAuth();
        }else{
            return new ResponseEntity<>(
                    new DataResponse(200, "TodoList Created", todoListService.create(request.getTask(), request.getTodoId(), request.getIsComplete()))
                    , HttpStatus.OK);
//            return todoListService.create(request.getTask(), request.getTodoId(), request.getIsComplete());
        }
    }

    @PutMapping("/{todoListId}")
    public ResponseEntity<DataResponse> edit(@PathVariable("todoListId") Long todoListId, @RequestBody TodoListRequest request){
        if(getAuthorization.getAuth().getStatusCode().is4xxClientError()){
            return getAuthorization.getAuth();
        }else {
            return new ResponseEntity<>(
                    new DataResponse(200, "TodoList Created", todoListService.edit(todoListId, request.getTask(),request.getTodoId(), request.getIsComplete()))
                    , HttpStatus.OK);
        }
//        return todoListService.edit(todoListId, request.getTask(),request.getTodoId(), request.getIsComplete());
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<DataResponse> delete(@PathVariable("todoId") Long todoId){

        if(getAuthorization.getAuth().getStatusCode().is4xxClientError()){
            return getAuthorization.getAuth();
        }else{
            return todoListService.delete(todoId);
        }
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<DataResponse> find(@PathVariable("todoId") Long todoId){
        if(getAuthorization.getAuth().getStatusCode().is4xxClientError()){
            return getAuthorization.getAuth();
        }else {
            return new ResponseEntity<>(
                    new DataResponse(200, "TodoList Created", todoListService.find(todoId))
                    , HttpStatus.OK);
        }
//        return todoListService.find(todoId);
    }
}

