package com.atguigu.spring6.autowired.controller;

import com.atguigu.spring6.autowired.service.UserService;
import com.atguigu.spring6.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
//    @Autowired
//    private UserService userService;

//    private UserService userService;
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

//    private UserService userService;
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

//    private UserService userService;
//
//    public UserController(@Autowired UserService userService) {
//        this.userService = userService;
//    }

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void add(){
        System.out.println("controller.....");
        userService.add();
    }
}
