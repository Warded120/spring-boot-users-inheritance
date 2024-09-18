package com.ivan.usercrud.service;

import com.ivan.usercrud.entity.Teacher;
import com.ivan.usercrud.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    TeacherRepository teacherRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.teacherRepository = teacherRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Teacher save(Teacher teacher) {

        teacher.setPassword(bCryptPasswordEncoder.encode(teacher.getPassword()));

        return teacherRepository.save(teacher);
    }
}
