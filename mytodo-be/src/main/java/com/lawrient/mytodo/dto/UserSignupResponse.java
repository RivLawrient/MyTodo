package com.lawrient.mytodo.dto;

import com.lawrient.mytodo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSignupResponse {

    private Integer code;

    private String status;

    private User data;
}
