package com.example.demo.api;

import com.example.demo.domain.User;
import com.example.demo.domain.request.UsersLoginParam;
import com.example.demo.domain.common.ResultObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * Created by xf on 2022/9/14.
 */
@Api(value = "用户管理接口", tags = "用户管理接口，提供用户的管理、查询接口")
public interface UserControllerApi {
    @ApiOperation("服务启动正常测试：查询用户信息，返回默认值")
    public String findUser();

    @ApiOperation("根据id查询用户信息")
    public User findById(Integer id);

    @ApiOperation("分页查询用户列表")
    public List<User> findAll(Integer page, Integer size);

    @ApiOperation(value = "登录以后返回token")
    public ResultObject login(UsersLoginParam users, BindingResult result);
}
