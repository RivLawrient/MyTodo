package com.lawrient.mytodo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataResponse {

    private Integer code;

    private String status;

    private Object data;
}
