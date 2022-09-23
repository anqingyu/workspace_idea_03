package com.example.demo.service;

import com.example.demo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    /**
     * 根据 ID 查询单条记录
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 新增用户
     * @param user
     * @return
     */
    User save(User user);

    /**
     * 删除用户
     * @param id
     */
    void delete(Integer id);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<User> findAll(Pageable pageable);

    /**
     * 名称模糊匹配
     * @param name
     * @return
     */
    List<User> findByNameLikeIgnoreCase(String name);

    String login(String userLogin, String userPass);
}
