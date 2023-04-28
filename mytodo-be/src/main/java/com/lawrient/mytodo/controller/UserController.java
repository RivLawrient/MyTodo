package com.lawrient.mytodo.controller;

import com.lawrient.mytodo.dto.UserSigninResponse;
import com.lawrient.mytodo.dto.UserSignup;
import com.lawrient.mytodo.dto.response.DataResponse;
import com.lawrient.mytodo.entity.User;
import com.lawrient.mytodo.security.GetAuthorization;
import com.lawrient.mytodo.service.AuthService;
import com.lawrient.mytodo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://mytodo.sannd.site")
@RestController
@RequestMapping("/auth")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GetAuthorization getAuthorization;

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<DataResponse> signup(@Valid @RequestBody UserSignup req){

//        AuthenticationEntryPoint authenticationEntryPoint;
//        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword());
//        Authentication auth = authReq.;
//        if(!userService.checkAuth(request)){
//            return new ResponseEntity<>(new DataResponse(401, "Unasuthorized: you need to login first", null), HttpStatus.UNAUTHORIZED);
//        }else {


            User user = new User(req.getEmail());
            user.setName(req.getName());
            user.setPassword(req.getPassword());

            return new ResponseEntity<>(new DataResponse(200, "User Created", userService.create(user)), HttpStatus.ACCEPTED);

//        }
//        User user = new User(req.getEmail());
//        user.setName(req.getName());
//        user.setPassword(req.getPassword());
//
//        String status;
//
//
//        return new UserSignupResponse(response.getStatus(), status, userService.create(user));

    }

    @GetMapping("/signin")
    public ResponseEntity<DataResponse> getUser() {
        return getAuthorization.getAuth();
    }



    @PostMapping("/signin")
//    public ResponseEntity<DataResponse> signin(@RequestBody UserSignin in, HttpServletResponse response, HttpServletRequest request){
    public ResponseEntity<DataResponse> signin(){
//        String user = auth.getAuth(request).getEmail();
//
//        if(!auth.checkAuth(request)){
//            return new ResponseEntity<>(new DataResponse(401,
//                                                        "Unauthorized: you need to login first",
//                                                        null), HttpStatus.UNAUTHORIZED);
//        }else {
//            if (userService.userExist(auth.getAuth(request).getEmail())){
//                if(userService.exist(new UserSignin(auth.getAuth(request).getEmail(),
//                                                    auth.getAuth(request).getPassword()))){
//                    return new ResponseEntity<>(new DataResponse(200,
//                                                                "User Successfull Authenticated",
//                                                                new UserSigninResponse(userService.getDetail(auth.getAuth(request).getEmail()).get().getEmail(),
//                                                                        userService.getDetail(auth.getAuth(request).getEmail()).get().getName())), HttpStatus.OK);
//                }else {
//                    return new ResponseEntity<>(new DataResponse(401,
//                            "Unauthorized: wrong password",
//                            null), HttpStatus.UNAUTHORIZED);
//                }
//            }else {
//                return new ResponseEntity<>(new DataResponse(401,
//                        "Unauthorized: wrong email",
//                        null), HttpStatus.UNAUTHORIZED);
//            }
//        }

        if (getAuthorization.getAuth().getStatusCode().is4xxClientError()){
            return getAuthorization.getAuth();
        }else {
//
            userService.login(authService.getEmail());

            return new ResponseEntity<>(
                    new DataResponse(200, "User Logged",
                            new UserSigninResponse(authService.getEmail(),
                                                    userService.getDetail(authService.getEmail()).get().getName(),
                                                    userService.getDetail(authService.getEmail()).get().getAuthorized() ))
                            , HttpStatus.OK);
        }


//        return getAuthorization.getAuth();


    }

    @PostMapping("/signout")
    public ResponseEntity<DataResponse> signout(){
        if (getAuthorization.getAuth().getStatusCode().is4xxClientError()){
            return getAuthorization.getAuth();
        }else {
//
            userService.logout(authService.getEmail());
            return new ResponseEntity<>(
                    new DataResponse(200, "User Logged",
                            new UserSigninResponse(authService.getEmail(),
                                                    userService.getDetail(authService.getEmail()).get().getName(),
                                                    userService.getDetail(authService.getEmail()).get().getAuthorized()))
                    , HttpStatus.OK);
        }
    }
}
