package com.maxwell.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maxwell.demo.dao.UserDao;
import com.maxwell.demo.dto.UserRegisterRequest;
import com.maxwell.demo.model.User;
import com.maxwell.demo.service.UserService;

@Component
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;

    @Override                                                                                          
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

}
