package com.ivan.usercrud.entity;

import com.ivan.usercrud.dto.StudentDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student extends User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "student_data_id", referencedColumnName = "id")
    StudentData studentData;

    public Student(StudentDto studentDto) {
        super(studentDto.getUsername(), studentDto.getPassword(), studentDto.isEnabled(), studentDto.getTopRole());
        studentData = new StudentData(studentDto.getFirstName(), studentDto.getLastName(), studentDto.getAge(), studentDto.getFatherLine());
    }
}
