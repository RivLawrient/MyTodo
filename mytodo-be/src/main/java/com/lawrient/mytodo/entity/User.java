package com.lawrient.mytodo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tbl_user")
public class User {

    @Id
    private String id;

    @Column(length = 25, nullable = false, unique = true)
    private String email;

    @Column(length = 30, nullable = false)
    private String name;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean authorized;

    public User(String email) {
        this.email = email;
        this.id = email;
    }
}
