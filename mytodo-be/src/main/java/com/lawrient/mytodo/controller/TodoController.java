package com.lawrient.mytodo.controller;

import com.lawrient.mytodo.dto.TodoRequest;
import com.lawrient.mytodo.dto.response.DataResponse;
import com.lawrient.mytodo.entity.Todo;
import com.lawrient.mytodo.security.GetAuthorization;
import com.lawrient.mytodo.service.AuthService;
import com.lawrient.mytodo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://mytodo.sannd.site")
@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private GetAuthorization getAuthorization;

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<DataResponse> create(@RequestBody TodoRequest title){

        if(getAuthorization.getAuth().getStatusCode().is4xxClientError()){
            return getAuthorization.getAuth();
        }else{
            return new ResponseEntity<>(
                    new DataResponse(200, "Todo Created", todoService.create(title.getTitle(), authService.getAuth().getEmail()))
                    , HttpStatus.OK);
//            return new DataResponse(200, "Todo Created", todoService.create(title.getTitle(), authService.getAuth().getEmail()));
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse> update(@RequestBody TodoRequest title, @PathVariable("id") Long id){
        if(getAuthorization.getAuth().getStatusCode().is4xxClientError()){
            return getAuthorization.getAuth();
        }else{
            return new ResponseEntity<>(
                    new DataResponse(200, "Todo change Saved", todoService.update(id, title.getTitle(), authService.getAuth().getEmail()))
                    , HttpStatus.OK);

        }
//        return todoService.update(id, title.getTitle(), "sand@gmail.com");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse> delete(@PathVariable("id") Long id){
        if(getAuthorization.getAuth().getStatusCode().is4xxClientError()){
            return getAuthorization.getAuth();
        }else{
            return todoService.delete(id);
        }
    }

    @GetMapping
    public ResponseEntity<DataResponse> find(){
//    public List<Todo> find(){
        if(getAuthorization.getAuth().getStatusCode().is4xxClientError()){
            return getAuthorization.getAuth();
        }else{
            return new ResponseEntity<>(
                    new DataResponse(200, "Success get list of Todo", todoService.find(authService.getAuth().getEmail()))
                    , HttpStatus.OK);
        }

//        return todoService.find("sand@gmail.com");
    }

//    @GetMapping("/")
//    public ResponseEntity<DataResponse> findById(@PathVariable("id") Long id){
//
//    }
}
