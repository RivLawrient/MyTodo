package com.lawrient.mytodo.security;

import com.lawrient.mytodo.dto.UserSignin;
import com.lawrient.mytodo.dto.UserSigninResponse;
import com.lawrient.mytodo.dto.response.DataResponse;
import com.lawrient.mytodo.service.AuthService;
import com.lawrient.mytodo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetAuthorization {

//    @Autowired
//    private HttpServletRequest request;

    @Autowired
    private AuthService auth;

    @Autowired
    private UserService userService;

//    public ResponseEntity<DataResponse> getAuth(){
//        String user = auth.getAuth(request).getEmail();
//
//        if(!auth.checkAuth(request)){
//            return new ResponseEntity<>(new DataResponse(401,
//                    "Unauthorized: you need to login first",
//                    null), HttpStatus.UNAUTHORIZED);
//        }else {
//            if (userService.userExist((auth.getAuth(request).getEmail()))){
//                if(userService.exist(new UserSignin(auth.getAuth(request).getEmail(),
//                        auth.getAuth(request).getPassword()))){
//                    return new ResponseEntity<>(new DataResponse(200,
//                            "User Successfull Authenticated",
//                            new UserSigninResponse(userService.getDetail(auth.getAuth(request).getEmail()).get().getEmail(),
//                                    userService.getDetail(auth.getAuth(request).getEmail()).get().getName())), HttpStatus.OK);
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
//    }



    public ResponseEntity<DataResponse> getAuth(){
        String user = auth.getAuth().getEmail();

        if(!auth.checkAuth()){
            return new ResponseEntity<>(new DataResponse(401,
                                                        "Unauthorized: you need to login first",
                                                        null), HttpStatus.UNAUTHORIZED);
        }else {
            if (userService.userExist((user))){
                if(userService.exist(new UserSignin(user, auth.getAuth().getPassword()))){
                    return new ResponseEntity<>(new DataResponse(200,
                                                                "User Successfull Authenticated",
                                                                new UserSigninResponse(userService.getDetail(user).get().getEmail(),
                                                                                       userService.getDetail(user).get().getName(),
                                                                                        userService.getDetail(user).get().getAuthorized())
                                                                                       ), HttpStatus.OK);
                }else {
                    return new ResponseEntity<>(new DataResponse(401,
                                                                "Unauthorized: wrong password",
                                                                null), HttpStatus.UNAUTHORIZED);
                }
            }else {
                return new ResponseEntity<>(new DataResponse(401,
                                                            "Unauthorized: wrong email",
                                                            null), HttpStatus.UNAUTHORIZED);
            }
        }
    }
}
