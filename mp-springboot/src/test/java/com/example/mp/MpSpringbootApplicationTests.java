package com.example.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mp.mapper.UserMapper;
import com.example.mp.pojo.User;
import com.example.mp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MpSpringbootApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

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

    /**
     * 比较操作
     */
    @Test
    public void testEq(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,user_name,password,name,age,email FROM tb_user WHERE
        // password = ? AND age >= ? AND name IN (?,?,?)
        wrapper.eq("password", "123456")
                .ge("age", 20)
                .in("name", "李四", "王五", "赵六");
        List<User> users = this.userMapper.selectList(wrapper);
        users.stream().forEach(s -> System.out.println(s));
    }

    /**
     * 模糊查询
     */
    @Test
    public void testLike(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //SELECT id,user_name,password,name,age,email FROM tb_user WHERE name LIKE ?
        // Parameters: %曹%(String)
        queryWrapper.like("name", "曹");
        this.userMapper.selectList(queryWrapper).stream().forEach(s -> System.out.println(s));
    }

    /**
     * 排序：
     * orderBy
     *   排序：ORDER BY 字段, ...
     *   例: orderBy(true, true, "id", "name") ---> order by id ASC,name ASC
     * orderByAsc
     *   排序：ORDER BY 字段, ... ASC
     *   例: orderByAsc("id", "name") ---> order by id ASC,name ASC
     * orderByDesc
     *   排序：ORDER BY 字段, ... DESC
     *   例: orderByDesc("id", "name") ---> order by id DESC,name DESC
     */
    @Test
    public void testOrderBy(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,user_name,password,name,age,email FROM tb_user ORDER BY age DESC
        wrapper.orderByDesc("age");
        this.userMapper.selectList(wrapper).forEach(s -> System.out.println(s));
    }

    /**
     * 逻辑查询：
     * or
     *   拼接 OR
     *   主动调用 or 表示紧接着下一个方法不是用 and 连接!(不调用 or 则默认为使用 and 连接)
     * and
     *   AND 嵌套
     *   例: and(i -> i.eq("name", "李白").ne("status", "活着")) ---> and (name = '李白' and status <> '活着')
     */
    @Test
    public void testOr(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,user_name,password,name,age,email FROM tb_user WHERE
        //name = ? OR age = ?
        wrapper.eq("name", "李四").or().eq("age", 24);
        this.userMapper.selectList(wrapper).stream().forEach(s -> System.out.println(s));
    }

    /**
     * 分页查询
     */
    @Test
    public void selectPage(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 年龄大于20岁
        wrapper.gt("age", 20);

        Page<User> page = new Page<>(2, 1);

        Page<User> userPage = this.userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数：" + userPage.getTotal());
        System.out.println("总页数：" + userPage.getPages());

        List<User> users = userPage.getRecords();
        users.forEach(s -> System.out.println(s));
    }

    // 自定义条件修改
    @Test
    public void testUpdate(){
        // 第一种更新方式：将需要更新的字段，设置到entity 中
        User user = new User();
        user.setAge(30);
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id", "1573998744700653571");
        this.userMapper.update(user, userUpdateWrapper);

        // 第二种：将entity设置为 null ，将需要更新的字段设置到 UpdateWrapper 中
        UpdateWrapper<User> userUpdateWrapper2 = new UpdateWrapper<>();
        userUpdateWrapper2.set("age", "29").eq("id", "1573998744700653570");
        this.userMapper.update(null, userUpdateWrapper2);
    }

    // 条件删除
    @Test
    public void testDelete(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 6L);
        int delete = this.userMapper.delete(updateWrapper);
        System.out.println(delete);
    }

    // 测试Mybatis-Plus的Service封装：save(T entity)
    @Test
    public void testUserServiceInsert(){
        User user = new User();
        user.setEmail("13542563333@163.com");
        user.setAge(35);
        user.setUserName("liu bei");
        user.setName("刘备");
        user.setPassword("123456");

        boolean save = userService.save(user);
        System.out.println(save);
    }

    // 测试Mybatis-Plus的Service封装:getById(Serializable id)
    @Test
    public void testUserServiceSelectById(){
        User user = userService.getById(3L);
        System.out.println(user);
    }

    // 测试Mybatis-Plus的Service封装:updateById(T entity)
    @Test
    public void testUserServiceUpdateById(){
        User user = new User();
        user.setId(1573998744700653572L);
        user.setAge(40);
        boolean b = userService.updateById(user);
        System.out.println(b);
    }

    // 测试Mybatis-Plus的Service封装:removeById(Serializable id)
    @Test
    public void testUserServiceDeleteById(){
        boolean b = userService.removeById(6L);
        System.out.println(b);
    }
}
