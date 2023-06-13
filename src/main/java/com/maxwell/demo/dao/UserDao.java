package com.maxwell.demo.dao;

import com.maxwell.demo.dto.UserRegisterRequest;
import com.maxwell.demo.model.User;

public interface UserDao {
    
    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User getUserByEmail(String email);
}
