package com.andrewd.libs.user.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;

import java.util.Set;

import lombok.Data;

@Entity
@Data
@Table(indexes = {@Index(name = "USER_NAME_IDX", columnList = "userName")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    private String email;

    private String password;

    private Role role;
}
