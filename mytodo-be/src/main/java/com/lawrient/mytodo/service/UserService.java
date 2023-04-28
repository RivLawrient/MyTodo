package com.lawrient.mytodo.service;

import com.lawrient.mytodo.dto.UserSignin;
import com.lawrient.mytodo.dto.UserSigninResponse;
import com.lawrient.mytodo.dto.response.DataResponse;
import com.lawrient.mytodo.entity.User;
import com.lawrient.mytodo.exception.WrongTermsExceptions;
import com.lawrient.mytodo.repository.UserRepository;
import com.lawrient.mytodo.util.EncryptPassword;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncryptPassword encryptPassword;

    @Autowired
    private AuthService auth;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public User create(User user){
        if(userRepository.existsUserByEmail(user.getEmail())){
            throw new RuntimeException("Account with Email :" +user.getEmail()+ " is already exist");
        }
        String password = encryptPassword.bCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        user.setAuthorized(false);
        return userRepository.save(user);
    }

    public boolean exist(UserSignin login) {
        return encryptPassword.bCryptPasswordEncoder()
                .matches(login.getPassword(),
                        userRepository.findById(login.getEmail()).get().getPassword());

    }

    public boolean userExist(String user){
        return userRepository.existsUserByEmail(user);
    }

    public Optional<User> getDetail (String email){
        return userRepository.findById(email);
    }

    public User login(String email){
        User user = new User(email);
        user.setId(userRepository.findById(email).get().getId());
        user.setName(userRepository.findById(email).get().getName());
        user.setPassword(userRepository.findById(email).get().getPassword());
        user.setAuthorized(true);

        return userRepository.save(user);
    }

    public User logout(String email){
        User user = new User(email);
        user.setId(userRepository.findById(email).get().getId());
        user.setName(userRepository.findById(email).get().getName());
        user.setPassword(userRepository.findById(email).get().getPassword());
        user.setAuthorized(false);

        return userRepository.save(user);
    }
}
