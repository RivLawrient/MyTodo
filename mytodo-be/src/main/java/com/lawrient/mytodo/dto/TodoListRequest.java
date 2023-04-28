package com.lawrient.mytodo.dto;

import lombok.Data;

@Data
public class TodoListRequest {

    private String task;

    private Long todoId;

    private Boolean isComplete;
}
