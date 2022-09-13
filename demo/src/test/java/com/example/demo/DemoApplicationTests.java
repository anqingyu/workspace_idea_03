package com.example.demo;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
@Slf4j
class DemoApplicationTests {
    static Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Resource
    private JdbcTemplate jdbcTemplate;
    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        logger.info("日志测试……");
        System.out.println(System.getProperty("user.home"));
    }

    @Test
    void contextLoads2() {
        String sql ="select * from user";
        List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setAge(rs.getInt("age"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
        log.info("查询成功");
        log.info("用户{}",users);
    }

    @Test
    void contextLoads3() {
        // 查询所有
        userService.findAll().stream().forEach(user -> log.info("查询所有{}", user));

        // 新增数据
        userService.save(new User().setId(2).setAge(12).setName("沉默王三").setPassword("123456"));
        userService.save(new User().setId(3).setAge(12).setName("沉默王四").setPassword("123456"));
        userService.save(new User().setId(4).setAge(12).setName("沉默王五").setPassword("123456"));
        userService.save(new User().setId(5).setAge(12).setName("沉默王五").setPassword("123456"));

        // 分页查询
        userService.findAll(PageRequest.of(1,2)).stream().forEach(user -> log.info("分页查询{}", user));
        // 模糊查询
        log.info("模糊查询{}",userService.findByNameLikeIgnoreCase("沉默%"));
        // 删除
        userService.delete(4);
    }

}
