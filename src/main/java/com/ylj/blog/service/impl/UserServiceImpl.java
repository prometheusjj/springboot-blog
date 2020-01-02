package com.ylj.blog.service.impl;

import com.ylj.blog.dao.UserMapper;
import com.ylj.blog.entity.User;
import com.ylj.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    public int regist(User user) {
        return userMapper.regist(user);
    }

    public User login(String name, String password) {
        User user = new User();
        user.setEmail( name );
        user.setPassword( password );
        return userMapper.login(name,password );
        //return userMapper.findUserByNameAndPwd( name,password );
    }

    public User findByEmail(String email) {
        User user = new User();
        user.setEmail( email );
        return userMapper.findByEmail( email );
        // return userMapper.findByEmail(email);
    }

    @Override
    public User findByPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        return userMapper.findByPhone(phone);
    }

    @Override
    public User findById(Long id) {
        User user = new User();
        user.setId(id);
        return userMapper.findById(id);
    }

    @Override
    public void deleteByEmail(String email) {
        userMapper.deleteByEmail(email);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }


}
