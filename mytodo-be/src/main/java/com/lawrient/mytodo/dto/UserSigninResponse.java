package com.lawrient.mytodo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSigninResponse {

    private String email;

    private String name;

    private Boolean authorizated;
}
