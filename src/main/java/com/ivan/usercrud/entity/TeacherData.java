package com.ivan.usercrud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "teacher_data")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TeacherData extends UserData {

    @Column(name = "wife_name")
    private String wifeName;

    @Column(name = "children_amount")
    private int childrenAmount;

    public TeacherData(String firstName, String lastName, int age, String wifeName, int childrenAmount) {
        super(firstName, lastName, age);
        this.wifeName = wifeName;
        this.childrenAmount = childrenAmount;
    }
}
