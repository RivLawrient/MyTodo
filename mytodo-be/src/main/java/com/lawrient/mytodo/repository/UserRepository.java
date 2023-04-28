package com.lawrient.mytodo.repository;

import com.lawrient.mytodo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsUserByEmail(String email);
}
