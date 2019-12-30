package com.ylj.blog.controller;

import com.ylj.blog.entity.User;
import com.ylj.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        System.out.println(">>>>>");
        return "test";
    }

    @RequestMapping("/findByAll")
    @ResponseBody
    public List<User> findByAll(){
        return userService.findAll();
    }
}
