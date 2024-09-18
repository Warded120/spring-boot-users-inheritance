package com.ivan.usercrud.controller;

import com.ivan.usercrud.dto.StudentDto;
import com.ivan.usercrud.dto.TeacherDto;
import com.ivan.usercrud.entity.Student;
import com.ivan.usercrud.entity.Teacher;
import com.ivan.usercrud.service.StudentService;
import com.ivan.usercrud.service.TeacherService;
import com.ivan.usercrud.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {

    UserService userService;
    StudentService studentService;
    TeacherService teacherService;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegisterController(UserService userService, StudentService studentService, TeacherService teacherService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Value("${user.roles}")
    List<String> roles;

    @GetMapping("/show")
    public String show(Model theModel) {
        return "user-form";
    }

    @GetMapping("/show-student")
    public String showRegular(Model theModel) {

        theModel.addAttribute("student", new StudentDto());
        theModel.addAttribute("roles", roles);

        return "user-student-form";
    }

    @PostMapping("/save-student")
    public String save(@ModelAttribute(name = "student") StudentDto studentDto
                        , HttpSession session
                        , RedirectAttributes attributes) {

        System.out.println("saving student");

        Student student = new Student(studentDto);
        Student dbStudent = studentService.save(student);

        attributes.addFlashAttribute("student", dbStudent);

        session.setAttribute("user", dbStudent);
        session.setAttribute("userType", "student");


        return "redirect:/register/confirm-student";
    }

    @GetMapping("/confirm-student")
    public String confirmStudent(@ModelAttribute("student") Student student) {
        return "user-student-form-confirmation";
    }

    @GetMapping("/show-teacher")
    public String showExtended(Model theModel) {

        theModel.addAttribute("teacher", new TeacherDto());
        theModel.addAttribute("roles", roles);

        return "user-teacher-form";
    }

    @PostMapping("/save-teacher")
    public String save(@ModelAttribute(name = "teacher") TeacherDto teacherDto
                        , HttpSession session
                        , RedirectAttributes attributes) {

        System.out.println("saving teacher");
        Teacher teacher = new Teacher(teacherDto);
        Teacher dbTeacher = this.teacherService.save(teacher);

        attributes.addFlashAttribute("teacher", dbTeacher);

        session.setAttribute("user", dbTeacher);
        session.setAttribute("userType", "teacher");

        return "redirect:/register/confirm-teacher";
    }

    @GetMapping("/confirm-teacher")
    public String confirmTeacher() {
        return "user-teacher-form-confirmation";
    }
}
