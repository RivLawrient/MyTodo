package com.lawrient.mytodo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_todolist")
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String task;

    @ManyToOne
    private Todo todo;

    @Column(nullable = false)
    private Boolean isComplete;
}
