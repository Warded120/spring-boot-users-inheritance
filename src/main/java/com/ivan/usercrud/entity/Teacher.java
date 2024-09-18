package com.ivan.usercrud.entity;

import com.ivan.usercrud.dto.TeacherDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "teacher")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Teacher extends User{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "teacher_data_id", referencedColumnName = "id")
    TeacherData teacherData;

    public Teacher(TeacherDto teacherDto) {
        super(teacherDto.getUsername(), teacherDto.getPassword(), teacherDto.isEnabled(), teacherDto.getTopRole());
        this.teacherData = new TeacherData(teacherDto.getFirstName(), teacherDto.getLastName(), teacherDto.getAge(), teacherDto.getWifeName(), teacherDto.getChildrenAmount());
    }
}
