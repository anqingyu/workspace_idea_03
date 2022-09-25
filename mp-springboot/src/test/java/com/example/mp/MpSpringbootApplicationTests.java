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

    @Test public void testInsert(){
        User user = new User();
        user.setEmail("123@itcast.cn");
        user.setAge(301);
        user.setUserName("caocao");
        user.setName("曹操");
        user.setPassword("123456");

        //返回的result是受影响的行数，并不是自增后的id
        int result = userMapper.insert(user);
        System.out.println("result = " + result);

        //自增后的id会回填到对象中
        System.out.println(user.getId());
    }

    @Test public void testUpdateById() {
        User user = new User();
        //主键
        user.setId(6L);
        //更新的字段
        user.setAge(21);

        // 根据id更新，更新不为null的字段
        this.userMapper.updateById(user);
    }

    @Test
    public void testDeleteById() {
        //执行删除操作
        int result = this.userMapper.deleteById(6L);
        System.out.println("result = " + result);
    }

}
