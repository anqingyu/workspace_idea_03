package com.example.mp;

import com.example.mp.mapper.UserMapper;
import com.example.mp.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MpSpringbootApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //根据id查询数据
        User user = userMapper.selectById(2L);
        System.out.println("输出user:" + user);
    }

}
