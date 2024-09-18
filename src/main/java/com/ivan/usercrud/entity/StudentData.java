package com.ivan.usercrud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "student_data")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class StudentData extends UserData {
    @Column(name = "father_line")
    private String fatherLine;

    public StudentData(String firstName, String lastName, int age, String fatherLine) {
        super(firstName, lastName, age);
        this.fatherLine = fatherLine;
    }
}
