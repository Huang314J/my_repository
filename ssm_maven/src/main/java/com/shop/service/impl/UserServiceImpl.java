package com.shop.service.impl;

import com.shop.dao.UserMapper;
import com.shop.domain.User;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getUserList() {
        List<User> userList = userMapper.selectByExample(null);
        return userList;
    }
}
