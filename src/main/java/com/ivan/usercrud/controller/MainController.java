package com.ivan.usercrud.controller;

import com.ivan.usercrud.entity.User;
import com.ivan.usercrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class MainController {

    UserService userService;

    @Value("${user.roles}")
    List<String> roles;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/main")
    public String index() {
        return "main-page";
    }
}
