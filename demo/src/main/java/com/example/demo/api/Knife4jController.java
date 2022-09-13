package com.example.demo.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试 Knife4j")
@RestController
@RequestMapping("/knife4j")
public class Knife4jController {
    @ApiOperation("swagger案例测试")
    @RequestMapping(value ="/test", method = RequestMethod.POST)
    public String test() {
        return "人性不变，历史一定会重演！";
    }
}
