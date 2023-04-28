package com.lawrient.mytodo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserSignup {

    @NotEmpty(message = "We need your email")
    private String email;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Password is required")
    private String password;
}
