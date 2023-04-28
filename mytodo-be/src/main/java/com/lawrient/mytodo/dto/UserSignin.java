package com.lawrient.mytodo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSignin {

    private String email;

    private String password;
}
