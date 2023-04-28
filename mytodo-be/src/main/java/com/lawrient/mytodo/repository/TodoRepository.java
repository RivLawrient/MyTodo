package com.lawrient.mytodo.repository;

import com.lawrient.mytodo.entity.Todo;
import com.lawrient.mytodo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByUser(User user);
}
