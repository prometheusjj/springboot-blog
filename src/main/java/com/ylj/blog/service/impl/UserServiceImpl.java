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
}
