package com.ivan.usercrud.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentDto {
    int id = 0;
    String username;
    String password;
    String confirmPassword;
    boolean enabled = true;
    String topRole;
    String firstName;
    String lastName;
    int age;
    String fatherLine;
}
