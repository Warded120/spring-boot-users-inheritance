package com.ivan.usercrud.service;

import com.ivan.usercrud.entity.Student;
import com.ivan.usercrud.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.studentRepository = studentRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Student save(Student student) {

        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));

        return studentRepository.save(student);
    }
}
