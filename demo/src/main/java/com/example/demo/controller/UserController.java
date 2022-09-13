package com.example.demo.controller;

import com.example.demo.api.UserControllerApi;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController implements UserControllerApi {
    private final UserService userService;

    // 构造器注入
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("findUser")
    public String findUser(){
        return "zhang san";
    }

    @GetMapping("/findUser/{id}")
    public User findById(@PathVariable Integer id){
        User user = userService.findById(id);
        return user;
    }

    @PostMapping("/findAll")
    public List<User> findAll(Integer page, Integer size){
        Page<User> all = userService.findAll(PageRequest.of(page, size));
        return all.toList();
    }
}
