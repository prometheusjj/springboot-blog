package com.ylj.blog.service;

import com.ylj.blog.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 测试查询所有数据
     * @return
     */
    List<User> findAll();

    /**
     * 用户注册
     * @param user
     * @return
     */
    int regist(User user);

    /**
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    User login(String email,String password);

    /**
     * 根据用户邮箱查询用户
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * 根据用户手机号查询用户
     * @param phone
     * @return
     */
    User findByPhone(String phone);

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 根据用户邮箱删除用户
     * @param email
     */
    void deleteByEmail(String email);

    /**
     * 更新用户信息
     * @param user
     */
    void update(User user);

}
