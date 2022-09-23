package com.example.demo.controller;

import com.example.demo.api.UserControllerApi;
import com.example.demo.domain.User;
import com.example.demo.domain.request.UsersLoginParam;
import com.example.demo.domain.common.ResultObject;
import com.example.demo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@Controller
//@RestController
public class UserController implements UserControllerApi {
    private final UserService userService;

    // 构造器注入
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("findUser")
    @ResponseBody
    public String findUser(){
        return "zhang san";
    }

    @GetMapping("/findUser/{id}")
    @ResponseBody
    public User findById(@PathVariable Integer id){
        User user = userService.findById(id);
        return user;
    }

    @PostMapping("/findAll")
    @ResponseBody
    public List<User> findAll(Integer page, Integer size){
        Page<User> all = userService.findAll(PageRequest.of(page, size));
        return all.toList();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject login(@Validated UsersLoginParam users, BindingResult result) {
        // 用户密码校验，并生成token
        String token = userService.login(users.getUserLogin(), users.getUserPass());
        if (token == null) {
            return ResultObject.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return ResultObject.success(tokenMap);
    }

    @RequestMapping(value = "/login2", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject login2(@Validated @RequestBody UsersLoginParam users, BindingResult result) {
        // 用户密码校验，并生成token
        String token = userService.login(users.getUserLogin(), users.getUserPass());
        if (token == null) {
            return ResultObject.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return ResultObject.success(tokenMap);
    }
}
