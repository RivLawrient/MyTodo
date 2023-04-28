package com.lawrient.mytodo.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
public class EncryptPassword {

    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    public String encode(String password){
//
//    }
//
//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        String password = "mypassword";
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        md.update(password.getBytes());
//        byte[] digest = md.digest();
//        StringBuilder hexString = new StringBuilder();
//        for (byte b : digest) {
//            hexString.append(String.format("%02x", b));
//        }
//        String hashedPassword = hexString.toString();
//        System.out.println(hashedPassword);
//    }
}
