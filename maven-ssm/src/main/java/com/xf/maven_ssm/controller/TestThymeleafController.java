package com.xf.maven_ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/testThymeleaf")
public class TestThymeleafController {
    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("returnData", "testThymeleaf");
        return "thymeleaf/testThymeleaf";
//        return "testThymeleaf.html";
    }
}
