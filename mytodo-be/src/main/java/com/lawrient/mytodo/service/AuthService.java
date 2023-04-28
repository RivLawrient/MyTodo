package com.lawrient.mytodo.service;

import com.lawrient.mytodo.dto.UserSignin;
import com.lawrient.mytodo.dto.UserSigninResponse;
import com.lawrient.mytodo.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
//@Component
public class AuthService {

    @Autowired
    private HttpServletRequest request;

    public boolean checkAuth(){
        String hasil = request.getHeader("Authorization");
        if(hasil == null){
            return false;
        }return true;
    }

    public UserSignin getAuth(){
        String data = request.getHeader("Authorization");

//        return request.getHeader("Authorization");
        if(data == null){
            return new UserSignin(null, null);
        }else{
            String hasil = data.split(" ")[1];
            String raw = new String(Base64.getDecoder().decode(hasil));
            try {
                String email = raw.split(":")[0];
                String password = raw.split(":")[1];
                return new UserSignin(email, password);
            }catch (Exception e){
                return new UserSignin(null, null);
            }
        }
    }

    public String getEmail(){
        return getAuth().getEmail();
    }

}
